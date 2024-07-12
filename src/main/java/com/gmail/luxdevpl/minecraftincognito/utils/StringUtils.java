package com.gmail.luxdevpl.minecraftincognito.utils;

import org.bukkit.ChatColor;

public class StringUtils {

    public static String color(String text) {
        return text != null ? ChatColor.translateAlternateColorCodes('&', text) : "";
    }
}
