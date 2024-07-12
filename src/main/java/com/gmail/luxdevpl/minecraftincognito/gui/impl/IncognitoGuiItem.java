package com.gmail.luxdevpl.minecraftincognito.gui.impl;

import com.gmail.luxdevpl.minecraftincognito.MinecraftIncognito;
import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;
import com.gmail.luxdevpl.minecraftincognito.gui.GuiItem;
import com.gmail.luxdevpl.minecraftincognito.gui.ItemClickEvent;
import com.gmail.luxdevpl.minecraftincognito.gui.types.IncognitoGuiItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class IncognitoGuiItem extends GuiItem {

    private final IncognitoUser incognitoUser;

    private final IncognitoGuiItems incognitoGuiItem;

    public IncognitoGuiItem(IncognitoGuiItems guiItem, IncognitoUser incognitoUser) {
        super(guiItem.getDisplayName(), guiItem.getLore(), guiItem.getIcon());

        this.incognitoUser = incognitoUser;
        this.incognitoGuiItem = guiItem;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        Player player = event.getPlayer();

        switch (incognitoGuiItem) {
            case ENABLE_NORMAL_RANDOM_NICK:
                incognitoUser.setNormalRandomNickname();
                incognitoUser.setIncognitoEnabled(true);

                MinecraftIncognito.getInstance().getIncognitoPacketHandler().updatePlayer(player);
                player.sendMessage(ChatColor.GREEN + "Twój nick został zmieniony na losowy normalny nick: " + incognitoUser.getFakeNickname());
                break;

            case ENABLE_RANDOM_STRING:
                incognitoUser.setRandomNickname();
                incognitoUser.setIncognitoEnabled(true);

                MinecraftIncognito.getInstance().getIncognitoPacketHandler().updatePlayer(player);
                player.sendMessage(ChatColor.GREEN + "Twój nick został zmieniony na losowy nick: " + incognitoUser.getFakeNickname());
                break;

            case DISABLE_INCOGNITO:
                if (incognitoUser.isIncognitoEnabled()) {
                    player.sendMessage(ChatColor.GREEN + "Twój tryb incognito został wyłączony.");
                    incognitoUser.setIncognitoEnabled(false);
                    MinecraftIncognito.getInstance().getIncognitoPacketHandler().updatePlayer(player);
                } else {
                    player.sendMessage(ChatColor.RED + "Nie jesteś w trybie incognito.");
                }
                break;
            default:
                event.setWillClose(true);
                break;
        }

        event.setWillClose(true);
    }

}
