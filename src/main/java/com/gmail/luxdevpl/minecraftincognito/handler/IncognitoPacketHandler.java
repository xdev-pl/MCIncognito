package com.gmail.luxdevpl.minecraftincognito.handler;

import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.*;
import com.gmail.luxdevpl.minecraftincognito.MinecraftIncognito;
import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class IncognitoPacketHandler {

    private MinecraftIncognito plugin;

    public IncognitoPacketHandler(MinecraftIncognito minecraftIncognito) {
        this.plugin = minecraftIncognito;
    }

    public void registerPacketListener() {
        ProtocolManager protocolManager = plugin.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.PLAYER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                Set<EnumWrappers.PlayerInfoAction> actions = event.getPacket().getPlayerInfoActions().read(0);
                if (actions.contains(EnumWrappers.PlayerInfoAction.ADD_PLAYER)) {
                    List<PlayerInfoData> originalData = event.getPacket().getPlayerInfoDataLists().read(1);
                    List<PlayerInfoData> newData = updatePlayerInfoData(originalData);
                    event.getPacket().getPlayerInfoDataLists().write(1, newData);
                }
            }
        });
    }

    private List<PlayerInfoData> updatePlayerInfoData(List<PlayerInfoData> originalInfo) {
        return originalInfo.stream().map(data -> {
            if (data == null || data.getProfile() == null) {
                return data;
            }

            UUID uuid = data.getProfile().getUUID();
            Optional<IncognitoUser> optionalUser = MinecraftIncognito.getInstance().getUserManager().getIncognitoUser(uuid);

            if (optionalUser.isPresent() && optionalUser.get().isIncognitoEnabled()) {
                IncognitoUser incognitoUser = optionalUser.get();
                WrappedGameProfile newProfile = new WrappedGameProfile(uuid, incognitoUser.getFakeNickname());
                newProfile.getProperties().removeAll("textures");
                newProfile.getProperties().put("textures", new WrappedSignedProperty("textures", incognitoUser.getFakeSkinTexture(), incognitoUser.getFakeSkinTextureSignature()));
                return new PlayerInfoData(newProfile, data.getLatency(), data.getGameMode(), WrappedChatComponent.fromText(incognitoUser.getFakeNickname()));
            }
            return data;
        }).collect(Collectors.toList());
    }

    public void updatePlayer(Player player) {
        if (player == null || !player.isOnline()) {
            return;
        }

        Optional<IncognitoUser> optionalIncognitoUser = MinecraftIncognito.getInstance().getUserManager().getIncognitoUser(player.getUniqueId());
        if (!optionalIncognitoUser.isPresent()) {
            return;
        }

        IncognitoUser incognitoUser = optionalIncognitoUser.get();
        WrappedGameProfile profile = new WrappedGameProfile(player.getUniqueId(), player.getName());
        profile.getProperties().removeAll("textures");
        profile.getProperties().put("textures", new WrappedSignedProperty("textures", incognitoUser.getRealSkinTexture(), incognitoUser.getRealSkinTextureSignature()));

        sendProfileUpdate(profile, player);

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.hidePlayer(plugin, player);
            p.showPlayer(plugin, player);
        });
    }



    private void sendProfileUpdate(WrappedGameProfile profile, Player player) {
        ProtocolManager manager = MinecraftIncognito.getInstance().getProtocolManager();
        PacketContainer updatePacket = manager.createPacket(PacketType.Play.Server.PLAYER_INFO);

        EnumSet<EnumWrappers.PlayerInfoAction> actions = EnumSet.of(
                EnumWrappers.PlayerInfoAction.ADD_PLAYER,
                EnumWrappers.PlayerInfoAction.UPDATE_LATENCY,
                EnumWrappers.PlayerInfoAction.UPDATE_LISTED,
                EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
        updatePacket.getPlayerInfoActions().write(0, actions);

        PlayerInfoData data = new PlayerInfoData(
                profile,
                0,
                EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()),
                WrappedChatComponent.fromText(profile.getName()));
        updatePacket.getPlayerInfoDataLists().write(1, Collections.singletonList(data));

        Bukkit.getOnlinePlayers().forEach(p -> {
            try {
                manager.sendServerPacket(p, updatePacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
