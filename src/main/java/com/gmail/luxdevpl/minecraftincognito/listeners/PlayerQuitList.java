package com.gmail.luxdevpl.minecraftincognito.listeners;

import com.gmail.luxdevpl.minecraftincognito.MinecraftIncognito;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitList implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();

        MinecraftIncognito.getInstance().getUserManager().removeIncognitoUser(playerUUID);
    }
}
