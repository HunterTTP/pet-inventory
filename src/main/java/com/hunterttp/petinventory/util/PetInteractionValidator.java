package com.hunterttp.petinventory.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;

public class PetInteractionValidator {

    public static boolean isPet(Entity entity) {
        return entity instanceof TameableEntity;
    }

    public static boolean canInteractWithPetInventory(PlayerEntity player, TameableEntity pet) {
        return isTamedByPlayer(player, pet) && isPlayerSneaking(player);
    }

    private static boolean isTamedByPlayer(PlayerEntity player, TameableEntity pet) {
        return pet.getOwner() != null && pet.getOwner().getUuid().equals(player.getUuid());
    }

    private static boolean isPlayerSneaking(PlayerEntity player) {
        return player.isSneaking();
    }
}