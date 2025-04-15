package com.hunterttp.petinventory;

import com.hunterttp.petinventory.event.PetInteractionHandler;
import com.hunterttp.petinventory.init.ModComponents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class PetInventory implements ModInitializer {

	@Override
	public void onInitialize() {
		PetInteractionHandler.register();
	}
}