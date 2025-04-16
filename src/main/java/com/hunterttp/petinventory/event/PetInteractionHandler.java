package com.hunterttp.petinventory.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import static com.hunterttp.petinventory.init.ModComponents.getPetInventory;
import static com.hunterttp.petinventory.util.PetInteractionValidator.canInteractWithPetInventory;
import static com.hunterttp.petinventory.util.PetInteractionValidator.isPet;
import static net.minecraft.screen.GenericContainerScreenHandler.createGeneric9x3;
import static net.minecraft.text.Text.literal;

public class PetInteractionHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!isPet(entity)) {
                return ActionResult.PASS;
            }
            var pet = (TameableEntity) entity;
            if (!canInteractWithPetInventory(player, pet)) {
                return ActionResult.PASS;
            }
            displayPetInventoryToPlayer(player, pet);
            return ActionResult.SUCCESS;
        });
    }

    private static void displayPetInventoryToPlayer(PlayerEntity player, TameableEntity pet) {
        player.openHandledScreen(new NamedScreenHandlerFactory() {
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity p) {
                return createGeneric9x3(syncId, playerInventory, getPetInventory(pet));
            }

            @Override
            public Text getDisplayName() {
                return literal(getPetName(pet) + "'s Inventory");
            }
        });
    }

    private static String getPetName(TameableEntity pet) {
        if (pet.hasCustomName() && pet.getCustomName() != null) {
            return pet.getCustomName().getString();
        }
        return pet.getType().getName().getString();
    }
}