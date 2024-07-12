package com.gmail.luxdevpl.minecraftincognito.listeners;

import com.gmail.luxdevpl.minecraftincognito.MinecraftIncognito;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.UUID;

public class PlayerKickList implements Listener {

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();

        MinecraftIncognito.getInstance().getUserManager().removeIncognitoUser(playerUUID);
    }
}
