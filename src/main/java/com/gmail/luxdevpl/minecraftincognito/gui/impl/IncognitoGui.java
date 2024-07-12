package com.gmail.luxdevpl.minecraftincognito.gui.impl;

import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;
import com.gmail.luxdevpl.minecraftincognito.gui.GuiMenu;
import com.gmail.luxdevpl.minecraftincognito.gui.types.IncognitoGuiItems;
import com.gmail.luxdevpl.minecraftincognito.utils.StringUtils;

public class IncognitoGui extends GuiMenu {

    public IncognitoGui(IncognitoUser incognitoUser) {
        super(StringUtils.color("&aUstawienia trybu Incognito."), Size.fit(IncognitoGuiItems.values().length));

        for(IncognitoGuiItems item : IncognitoGuiItems.values()){
            IncognitoGuiItem incognitoGuiItem = new IncognitoGuiItem(item, incognitoUser);

            setItem(item.getSlotId(), incognitoGuiItem);
        }
    }
}
