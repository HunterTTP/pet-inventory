package com.hunterttp.petinventory.component;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import org.ladysnake.cca.api.v3.component.Component;

public class PetInventoryComponent implements Component {
    private final SimpleInventory inventory = new SimpleInventory(27);

    public SimpleInventory getInventory() {
        return inventory;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        var items = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        Inventories.readNbt(tag, items, registryLookup);
        for (int i = 0; i < items.size(); i++) {
            inventory.setStack(i, items.get(i));
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        var items = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        for (int i = 0; i < items.size(); i++) {
            items.set(i, inventory.getStack(i));
        }
        Inventories.writeNbt(tag, items, registryLookup);
    }
}