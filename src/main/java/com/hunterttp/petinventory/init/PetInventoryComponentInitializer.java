package com.hunterttp.petinventory.init;

import com.hunterttp.petinventory.component.PetInventoryComponent;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

import static org.ladysnake.cca.api.v3.component.ComponentRegistry.getOrCreate;

public class PetInventoryComponentInitializer implements EntityComponentInitializer {
    private static final Identifier COMPONENT_ID = Identifier.of("petinventory", "pet_inventory");
    private static final ComponentKey<PetInventoryComponent> PET_INVENTORIES = createPetInventoriesComponent();

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        addInventoriesToAllTameableCreatures(registry);
    }

    private static ComponentKey<PetInventoryComponent> createPetInventoriesComponent() {
        return getOrCreate(COMPONENT_ID, PetInventoryComponent.class);
    }

    private static void addInventoriesToAllTameableCreatures(EntityComponentFactoryRegistry registry) {
        registry.registerFor(TameableEntity.class, PET_INVENTORIES, entity -> new PetInventoryComponent());
    }

    public static SimpleInventory getPetInventory(TameableEntity pet) {
        return PET_INVENTORIES.get(pet).getInventory();
    }
}