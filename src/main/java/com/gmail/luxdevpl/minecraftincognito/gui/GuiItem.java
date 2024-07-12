package com.gmail.luxdevpl.minecraftincognito.gui;

import com.gmail.luxdevpl.minecraftincognito.utils.StringUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GuiItem {

    protected String name;
    protected List<String> lores;
    protected ItemStack icon;

    public GuiItem(String name, List<String> lores, ItemStack icon) {
        Validate.notNull(name, "Name cannot be null");
        Validate.notNull(lores, "Lore list cannot be null");
        Validate.notNull(icon, "Icon cannot be null");

        this.name = StringUtils.color(name);
        this.lores = lores.stream().map(StringUtils::color).collect(Collectors.toList());
        this.icon = icon;
    }

    public ItemStack getFinalIcon(Player viewer) {
        return setItemMeta(icon.clone());
    }

    public ItemStack getIcon() {
        return icon;
    }

    public abstract void onItemClick(ItemClickEvent event);

    protected ItemStack setItemMeta(ItemStack icon) {
        ItemMeta meta = icon.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lores);
            icon.setItemMeta(meta);
        }
        return icon;
    }

    public void updateItemMeta(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lores);
            item.setItemMeta(meta);
        }
    }
}
