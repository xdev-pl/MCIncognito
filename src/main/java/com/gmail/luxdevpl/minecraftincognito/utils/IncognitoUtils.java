package com.gmail.luxdevpl.minecraftincognito.utils;

import java.util.concurrent.ThreadLocalRandom;

public class IncognitoUtils {

    private static final String[] NICKNAMES = {
            "NightRider", "LoneWolf", "ShadowHunter", "GhostWarrior", "BladeRunner",
            "SkyCaptain", "DarkKnight", "SilverFox", "IronMaiden", "MasterChief",
            "GoldRaven", "ThunderStrike", "BlazeKing", "AquaMarine", "WindWalker"
    };

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static final String incognitoTexture = "eyJ0aW1lc3RhbXAiOjE1ODgwMjAxNDcxMDIsInByb2ZpbGVJZCI6IjU5YWFiNTJmMzBlMjQxY2RhN2MyMTdlYWUyZjIxOWQ2IiwicHJvZmlsZU5hbWUiOiJTclJhbWNldGkxMiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTc0YTUzYTU4NzBjOGQ3ZDY4NWE5YzA2Y2EwNzE2YTRlOTdhNjA4NjAwNzg4NWQ2NmNlZGMyNTBkNzhiZThkNCJ9fX0=";
    public static final String incognitoTextureSignature = "issNEx3LijmPkOtwSzy8T8SpMRsq442A5n1hFbpF2RnLv77u5ALYFIujr+Rhw14fFSUN/xA/Nc2KHqCyGQbxuB7zz4dSXGuao1xYfY28dLZ+4aNWSVeImcWGzajnW0IskFVPCgOMg5W3MqVy/La/auWGT8YT9z/tKNgfHIf63y9D3zy9e5iU76ufmRx/nMweYi3k/k8S9nlMv9Mkw7atWYcEbi6wdhdc1lM9jlZqmNytTQJXeJ8fkizezYOruF2TnrTpeJGA1zaPu1x55bokUxK6QPWX1pebUzPgwpTu7lV/1lSjDKtNm3XXNtwdChdWB/RB7hKz4TEj2egeYBLIKygvaoHaRxP6/o27xgPl+7Q25CVNicJhB+Oen0gDXfsH3bPAnjrIky64mBIcZSNSUCu9RoSI8ZCVZTpCT+xcRTK9xvi1pupGKppkena1qVuQN57k0nvGxheKoaugZsQgDsfp2x4TNOsSc2kRzYX2RPWBpVvowSYOYYHq15briGxFbKVauz8z6wr81BWivZpkvlKxRj0SFozIkpfqdQidaAiVIMryYHAPo3AwPVk2yvqLxlkdFUTYBVR6WRPBekEOL+IwRhzcRDQsWkA9zSDg3HyXowKjzP7yEo7RgV+zAyoA6kiOwqG81ocORRZvU0uKnvd+rj00SrFjU/lFabJUk6E=";

    public static String getNormalRandomNickname() {
        int randomIndex = ThreadLocalRandom.current().nextInt(NICKNAMES.length);
        return NICKNAMES[randomIndex];
    }

    public static String getRandomNickname() {
        int length = ThreadLocalRandom.current().nextInt(5, 13);
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }

        return sb.toString();
    }
}

