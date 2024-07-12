package com.gmail.luxdevpl.minecraftincognito;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.gmail.luxdevpl.minecraftincognito.commands.IncognitoCommand;
import com.gmail.luxdevpl.minecraftincognito.handler.IncognitoPacketHandler;
import com.gmail.luxdevpl.minecraftincognito.listeners.InventoryClickList;
import com.gmail.luxdevpl.minecraftincognito.listeners.PlayerJoinList;
import com.gmail.luxdevpl.minecraftincognito.listeners.PlayerKickList;
import com.gmail.luxdevpl.minecraftincognito.listeners.PlayerQuitList;
import com.gmail.luxdevpl.minecraftincognito.manager.IUserManager;
import com.gmail.luxdevpl.minecraftincognito.manager.UserManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.stream.Collectors;

public final class MinecraftIncognito extends JavaPlugin {

    private static MinecraftIncognito SINGLETON;

    private IUserManager userManager;

    private IncognitoPacketHandler incognitoPacketHandler;

    private ProtocolManager protocolManager;

    @Override
    public void onLoad() {
        SINGLETON = this;

        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void onEnable() {
        this.registerListeners();
        this.registerCommands();

        this.userManager = new UserManager();

        this.incognitoPacketHandler = new IncognitoPacketHandler(this);
        this.incognitoPacketHandler.registerPacketListener();

        getLogger().info("Incognito by ServerCreators is now enabled! dev discord: luxdevpl");
    }

    @Override
    public void onDisable() {
        getLogger().info("Incognito by ServerCreators is now disabled! dev discord: luxdevpl");
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinList(), this);
        pm.registerEvents(new PlayerQuitList(), this);
        pm.registerEvents(new PlayerKickList(), this);
        pm.registerEvents(new InventoryClickList(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("incognito")).setExecutor(new IncognitoCommand());
    }

    public IUserManager getUserManager() {
        return userManager;
    }

    public static MinecraftIncognito getInstance() {
        return SINGLETON;
    }

    public IncognitoPacketHandler getIncognitoPacketHandler() {
        return incognitoPacketHandler;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
