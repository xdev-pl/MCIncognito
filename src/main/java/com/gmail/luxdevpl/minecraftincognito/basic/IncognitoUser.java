package com.gmail.luxdevpl.minecraftincognito.basic;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.gmail.luxdevpl.minecraftincognito.utils.IncognitoUtils;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class IncognitoUser {

    private final UUID uuid;

    private String realNickname;
    private String fakeNickname;

    private String realSkinTexture;
    private String realSkinTextureSignature;

    private String fakeSkinTexture;
    private String fakeSkinTextureSignature;

    private boolean incognitoEnabled;

    public IncognitoUser(Player player) {
        this.uuid = player.getUniqueId();

        this.realNickname = player.getName();
        this.fakeNickname = "";

        this.realSkinTexture = "ewogICJ0aW1lc3RhbXAiIDogMTY5MTQ2MTc0NDUwMywKICAicHJvZmlsZUlkIiA6ICI2NTk0YzdiMTExOWE0Njc3ODc0Y2ZmOWNlMzM3NzYxOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNYXJzaG1lbGxvMjIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E0ZjVmMDc2YmVkZDhmOTU2NzZmMGJjN2JkMmQ1OGZlNmIzYzE2ODU1ZjgxM2VjMGJjYzdmNWUwNjFjMDZkYiIKICAgIH0KICB9Cn0=";
        this.realSkinTextureSignature = "Nx4ZjQPEKtzr0UVCJY3eaUVNO4ECi/MyVN2ezxiyVU45+Pwqc1BILnoMq9Q4YYl86At7FPGjQlo78rDvvEL2uZZdAmKLrsZOv9qf/xa7o3yEcfzaFVcts8z9Q6DUsQsEdZrQFlbiGWci6gPWpDriKcuu9WsOp040gtVe30Wf2EVlRh0u4rd4Dq1b/MpxXSRH2r4b78imneGuhawCudo+WkTjfdUH03Uoc8x7LrNwOsr/LOfUB2dBEBzXtnUlLjL2TIOAvybEF+jLKk+PPIRveT//GAnsOTZK4uHUyA7Ye8lm7rGMhy9V1KKvFa53NX3vhDhxnu08Yj8CmZEWOC7rF2gvupun5ZCL0qNlyc5UHF/V5b0RNONICPNfi0S8Pv0syG+8SSXveTI+c9Zop2Gx1PAf560Wq//N3xJmtxfQTJtJrmVBAqB8H+y3SZ6c42yjRVKm4sNmKokgH8mlswbFYRThNBfRniRpdm3rZwIsjjMIMGB+kVAGMTfQacRcU9Twaal64tljFq2F471a3EQccsAaiTS26wxd3Gtc6akMB2JqkBu8fK2iSQ1H0Qec5tSBWsbBu5LY39EuxdEFtnxgAhzUP1nZw9ptDf3+TqO6exOGcFOSO0Uysx3rWGbTHeqjr+5p8xbxjuIUE6K1iwSBnLLGcGIM+kL3J5vx2h9JLGk=";
        this.fakeSkinTexture = IncognitoUtils.incognitoTexture;//TODO
        this.fakeSkinTextureSignature = IncognitoUtils.incognitoTextureSignature;//TODO
        this.incognitoEnabled = false;

        WrappedGameProfile wrappedProfile = WrappedGameProfile.fromPlayer(player);
        Optional<WrappedSignedProperty> textureProperty = wrappedProfile.getProperties().get("textures").stream().findFirst();

        textureProperty.ifPresent(property -> {
            this.realSkinTexture = property.getValue();
            this.realSkinTextureSignature = property.getSignature();
        });
    }

    public void setNormalRandomNickname() {
        this.fakeNickname = IncognitoUtils.getNormalRandomNickname();
    }

    public void setRandomNickname() {
        this.fakeNickname = IncognitoUtils.getRandomNickname();
    }

    public void setIncognitoEnabled(boolean incognitoEnabled) {
        this.incognitoEnabled = incognitoEnabled;
    }

    public String getFakeNickname() {
        return fakeNickname;
    }

    public String getFakeSkinTexture() {
        return fakeSkinTexture;
    }

    public String getRealNickname() {
        return realNickname;
    }

    public String getRealSkinTexture() {
        return realSkinTexture;
    }

    public String getFakeSkinTextureSignature() {
        return fakeSkinTextureSignature;
    }

    public void setFakeSkinTexture(String fakeSkinTexture) {
        this.fakeSkinTexture = fakeSkinTexture;
    }

    public void setFakeSkinTextureSignature(String fakeSkinTextureSignature) {
        this.fakeSkinTextureSignature = fakeSkinTextureSignature;
    }

    public void setFakeNickname(String fakeNickname) {
        this.fakeNickname = fakeNickname;
    }

    public void setRealNickname(String realNickname) {
        this.realNickname = realNickname;
    }

    public void setRealSkinTexture(String realSkinTexture) {
        this.realSkinTexture = realSkinTexture;
    }

    public void setRealSkinTextureSignature(String realSkinTextureSignature) {
        this.realSkinTextureSignature = realSkinTextureSignature;
    }

    public boolean isIncognitoEnabled() {
        return incognitoEnabled;
    }

    public String getRealSkinTextureSignature() {
        return realSkinTextureSignature;
    }

    public UUID getUuid() {
        return uuid;
    }
}

