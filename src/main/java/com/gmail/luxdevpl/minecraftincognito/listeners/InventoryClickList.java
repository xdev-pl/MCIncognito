package com.gmail.luxdevpl.minecraftincognito.listeners;

import com.gmail.luxdevpl.minecraftincognito.gui.GuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickList implements Listener {

    @EventHandler
    public void handle(InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;

        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof GuiHolder) {
            e.setCancelled(true);
            ((GuiHolder) e.getInventory().getHolder()).getMenu().onInventoryClick(e);
        }
    }

}
