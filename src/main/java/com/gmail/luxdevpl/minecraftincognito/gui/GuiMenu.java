package com.gmail.luxdevpl.minecraftincognito.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GuiMenu {

    protected final String name;
    protected final Size size;
    protected GuiItem[] items;
    protected GuiMenu parent;

    public GuiMenu(String name, Size size, GuiMenu parent) {
        this.name = name;
        this.size = size;
        this.items = new GuiItem[size.getSize()];
        this.parent = parent;
    }

    public GuiMenu(String name, Size size) {
        this(name, size, null);
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public GuiMenu getParent() {
        return parent;
    }

    public void setParent(GuiMenu parent) {
        this.parent = parent;
    }

    public GuiMenu setItem(int position, GuiItem menuItem) {
        items[position] = menuItem;
        return this;
    }

    public GuiMenu fillEmptySlots(GuiItem menuItem) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = menuItem;
            }
        }
        return this;
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(new GuiHolder(this, Bukkit.createInventory(player, size.getSize())), size.getSize(), name);
        apply(inventory, player);
        player.openInventory(inventory);
    }


    public void update(Player player) {
        player.getOpenInventory();

        Inventory openInventory = player.getOpenInventory().getTopInventory();
        if (!isGuiMenuInventory(openInventory)) {
            return;
        }

        apply(openInventory, player);
        player.updateInventory();
    }

    private boolean isGuiMenuInventory(Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();
        if (holder instanceof GuiHolder) {
            GuiMenu menu = ((GuiHolder) holder).getMenu();
            return menu.equals(this);
        }
        return false;
    }

    private void apply(Inventory inventory, Player player) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) continue;
            if (items[i] != null) {
                inventory.setItem(i, items[i].getFinalIcon(player));
            }
        }
    }

    public void onInventoryClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();
        if (slot >= 0 && slot < size.getSize() && items[slot] != null) {
            Player player = (Player) event.getWhoClicked();
            ItemClickEvent itemClickEvent = new ItemClickEvent(player, event.getClick(), event.isRightClick(), event.isLeftClick(), event.isShiftClick());
            items[slot].onItemClick(itemClickEvent);
            if (itemClickEvent.willUpdate()) {
                update(player);
            } else {
                player.updateInventory();
                if (itemClickEvent.willClose() || itemClickEvent.willGoBack()) {
                    player.closeInventory();
                }
                if (itemClickEvent.willGoBack() && hasParent()) {
                    parent.open(player);
                }
            }
        }

    }

    public enum Size {
        ONE_LINE(9),
        TWO_LINE(18),
        THREE_LINE(27),
        FOUR_LINE(36),
        FIVE_LINE(45),
        SIX_LINE(54);

        private final int size;

        Size(int size) {
            this.size = size;
        }

        public static Size fit(int slots) {
            if (slots < 10) {
                return ONE_LINE;
            } else if (slots < 19) {
                return TWO_LINE;
            } else if (slots < 28) {
                return THREE_LINE;
            } else if (slots < 37) {
                return FOUR_LINE;
            } else if (slots < 46) {
                return FIVE_LINE;
            } else {
                return SIX_LINE;
            }
        }

        public int getSize() {
            return size;
        }
    }
}