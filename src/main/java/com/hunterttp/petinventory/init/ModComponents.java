package com.hunterttp.petinventory.init;

import com.hunterttp.petinventory.component.PetInventoryComponent;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModComponents implements EntityComponentInitializer {
    private static final ComponentKey<PetInventoryComponent> PET_INVENTORY =
            ComponentRegistry.getOrCreate(Identifier.of("petinventory", "pet_inventory"), PetInventoryComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(TameableEntity.class, PET_INVENTORY, entity -> new PetInventoryComponent());
    }

    public static SimpleInventory getPetInventory(TameableEntity pet) {
        return PET_INVENTORY.get(pet).getInventory();
    }
}