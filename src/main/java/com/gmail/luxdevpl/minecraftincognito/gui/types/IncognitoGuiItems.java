package com.gmail.luxdevpl.minecraftincognito.gui.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.gmail.luxdevpl.minecraftincognito.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum IncognitoGuiItems {

    ENABLE_NORMAL_RANDOM_NICK(0, new ItemStack(Material.NAME_TAG), "&7Włącz incognito z normalnym losowym nickiem", Arrays.asList("&aKliknij, aby włączyć incognito z normalnym losowym nickiem", "&aTwój skin automatycznie się zmieni")),
    ENABLE_RANDOM_STRING(4, new ItemStack(Material.NAME_TAG), "&7Włącz incognito z losowo wygenerowanym ciągiem znaków", Arrays.asList("&aKliknij, aby włączyć incognito z losowo wygenerowanym ciągiem znaków", "&aTwój skin automatycznie się zmieni")),
    DISABLE_INCOGNITO(8, new ItemStack(Material.BARRIER), "&7Wyłącz incognito", Collections.singletonList("&cKliknij, aby wyłączyć incognito"));

    private final int slotId;
    private final ItemStack icon;
    private final String displayName;
    private final List<String> lore;

    IncognitoGuiItems(int slotId, ItemStack icon, String displayName, List<String> lore) {
        this.slotId = slotId;
        this.icon = setItemMeta(icon, displayName, lore);
        this.displayName = displayName;
        this.lore = lore;
    }

    public int getSlotId() {
        return slotId;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return StringUtils.color(displayName);
    }

    public List<String> getLore() {
        return lore.stream().map(StringUtils::color).collect(Collectors.toList());
    }

    private ItemStack setItemMeta(ItemStack item, String displayName, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(StringUtils.color(displayName));
        meta.setLore(lore.stream().map(StringUtils::color).collect(Collectors.toList()));
        item.setItemMeta(meta);
        return item;
    }
}