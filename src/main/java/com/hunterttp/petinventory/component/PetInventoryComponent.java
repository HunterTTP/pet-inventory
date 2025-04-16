package com.hunterttp.petinventory.component;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.Component;

public class PetInventoryComponent implements Component {
    private SimpleInventory inventory;

    public SimpleInventory getInventory() {
        if (inventory == null) {
            inventory = new SimpleInventory(27);
        }
        return inventory;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(tag, getInventory().heldStacks, registryLookup);
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        if (inventory != null) {
            Inventories.writeNbt(tag, inventory.heldStacks, registryLookup);
        }
    }
}