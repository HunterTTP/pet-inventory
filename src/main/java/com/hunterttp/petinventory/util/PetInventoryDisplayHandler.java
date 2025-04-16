package com.hunterttp.petinventory.util;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

import static com.hunterttp.petinventory.init.PetInventoryComponentInitializer.getPetInventory;
import static net.minecraft.screen.GenericContainerScreenHandler.createGeneric9x3;
import static net.minecraft.text.Text.literal;

public class PetInventoryDisplayHandler {

    public static void displayPetInventoryToPlayer(PlayerEntity player, TameableEntity pet) {
        var petName = getPetName(pet);
        var petInventory = getPetInventory(pet);
        var inventoryScreen = buildPetInventoryScreen(petName, petInventory);
        player.openHandledScreen(inventoryScreen);
    }

    private static NamedScreenHandlerFactory buildPetInventoryScreen(String petName, SimpleInventory petInventory) {
        return new NamedScreenHandlerFactory() {
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity p) {
                return createGeneric9x3(syncId, playerInventory, petInventory);
            }

            @Override
            public Text getDisplayName() {
                return literal(petName + "'s Inventory");
            }
        };
    }

    private static String getPetName(TameableEntity pet) {
        if (pet.hasCustomName() && pet.getCustomName() != null) {
            return pet.getCustomName().getString();
        }
        return pet.getType().getName().getString();
    }
}