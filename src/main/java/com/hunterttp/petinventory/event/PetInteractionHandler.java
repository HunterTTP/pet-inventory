package com.hunterttp.petinventory.event;

import com.hunterttp.petinventory.init.ModComponents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class PetInteractionHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!(entity instanceof TameableEntity pet)) return ActionResult.PASS;
            if (!pet.isTamed() || !player.isSneaking()) return ActionResult.PASS;

            var owner = pet.getOwner();
            if (owner == null || !player.getUuid().equals(owner.getUuid())) return ActionResult.PASS;

            var component = ModComponents.PET_INVENTORY.get(pet);
            var inventory = component.getInventory();

            player.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity p) {
                    return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, inventory);
                }

                @Override
                public Text getDisplayName() {
                    return Text.literal("Pet Inventory");
                }
            });

            return ActionResult.SUCCESS;
        });
    }
}