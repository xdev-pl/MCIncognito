package com.gmail.luxdevpl.minecraftincognito.listeners;

import com.gmail.luxdevpl.minecraftincognito.MinecraftIncognito;
import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerJoinList implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            IncognitoUser newUser = new IncognitoUser(event.getPlayer());

            MinecraftIncognito.getInstance().getUserManager().addIncognitoUser(event.getPlayer().getUniqueId(), newUser);
        } catch (IllegalStateException exception) {
            MinecraftIncognito.getInstance().getLogger().log(Level.SEVERE, "Wystąpił błąd podczas tworzenia IncognitoUser dla gracza: " + event.getPlayer().getName(), exception);

            event.getPlayer().kickPlayer(ChatColor.RED + "Wystąpił problem z twoim profilem incognito. Wejdz ponownie.");
        }
    }
}
