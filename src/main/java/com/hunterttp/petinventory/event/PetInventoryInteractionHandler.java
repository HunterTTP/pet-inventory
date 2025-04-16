package com.hunterttp.petinventory.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import static com.hunterttp.petinventory.util.PetInventoryDisplayHandler.displayPetInventoryToPlayer;

public class PetInventoryInteractionHandler {
    public static void registerPetInventoryInteraction() {
        UseEntityCallback.EVENT.register(PetInventoryInteractionHandler::handlePetInventoryInteraction);
    }

    private static ActionResult handlePetInventoryInteraction(PlayerEntity player,
                                                              World world,
                                                              Hand hand,
                                                              Entity entity,
                                                              EntityHitResult hitResult) {
        if (!(entity instanceof TameableEntity pet)) {
            return ActionResult.PASS;
        }
        if (!player.isSneaking()) {
            return ActionResult.PASS;
        }
        if (!isTamedByPlayer(player, pet)) {
            return ActionResult.PASS;
        }
        displayPetInventoryToPlayer(player, pet);
        return ActionResult.SUCCESS;
    }

    private static boolean isTamedByPlayer(PlayerEntity player, TameableEntity pet) {
        return pet.getOwner() != null && pet.getOwner().getUuid().equals(player.getUuid());
    }
}