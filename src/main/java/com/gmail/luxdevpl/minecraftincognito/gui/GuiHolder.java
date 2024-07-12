package com.gmail.luxdevpl.minecraftincognito.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class GuiHolder implements InventoryHolder {

    private final GuiMenu menu;
    private final Inventory inventory;

    public GuiHolder(GuiMenu menu, Inventory inventory) {
        this.menu = menu;
        this.inventory = inventory;
    }

    public GuiMenu getMenu() {
        return menu;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}

