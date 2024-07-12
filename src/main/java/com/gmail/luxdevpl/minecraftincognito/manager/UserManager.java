package com.gmail.luxdevpl.minecraftincognito.manager;

import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserManager implements IUserManager {

    private final Map<UUID, IncognitoUser> userMap;

    public UserManager() {
        this.userMap = new HashMap<>();
    }

    @Override
    public void addIncognitoUser(UUID uuid, IncognitoUser user) {
        userMap.put(uuid, user);
    }

    @Override
    public void removeIncognitoUser(UUID uuid) {
        userMap.remove(uuid);
    }

    @Override
    public Optional<IncognitoUser> getIncognitoUser(UUID uuid) {
        return Optional.ofNullable(userMap.get(uuid));
    }
}


