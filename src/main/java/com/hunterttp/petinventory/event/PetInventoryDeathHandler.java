package com.hunterttp.petinventory.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.world.ServerWorld;

import static com.hunterttp.petinventory.init.PetInventoryComponentInitializer.getPetInventory;

public class PetInventoryDeathHandler {
    public static void registerPetDeathInventoryBehavior() {
        ServerLivingEntityEvents.AFTER_DEATH.register(PetInventoryDeathHandler::handlePetDeathInventoryBehavior);
    }

    private static void handlePetDeathInventoryBehavior(Entity entity, DamageSource damageSource) {
        if (!(entity instanceof TameableEntity pet)) {
            return;
        }
        if (!(entity.getWorld() instanceof ServerWorld world)) {
            return;
        }
        var inventory = getPetInventory(pet);
        for (var stack : inventory.getHeldStacks()) {
            if (!stack.isEmpty()) {
                pet.dropStack(world, stack);
            }
        }
        inventory.clear();
    }
}