package com.hunterttp.petinventory;

import com.hunterttp.petinventory.event.PetInventoryInteractionHandler;
import net.fabricmc.api.ModInitializer;

public class PetInventory implements ModInitializer {

    @Override
    public void onInitialize() {
        PetInventoryInteractionHandler.registerPetInventoryInteraction();
    }
}