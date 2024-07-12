package com.gmail.luxdevpl.minecraftincognito.manager;

import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;

import java.util.Optional;
import java.util.UUID;

public interface IUserManager {

    void addIncognitoUser(UUID uuid, IncognitoUser user);
    void removeIncognitoUser(UUID uuid);
    Optional<IncognitoUser> getIncognitoUser(UUID uuid);

}
