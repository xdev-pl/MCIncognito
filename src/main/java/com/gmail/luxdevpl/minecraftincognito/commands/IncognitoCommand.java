package com.gmail.luxdevpl.minecraftincognito.commands;

import com.gmail.luxdevpl.minecraftincognito.MinecraftIncognito;
import com.gmail.luxdevpl.minecraftincognito.basic.IncognitoUser;
import com.gmail.luxdevpl.minecraftincognito.gui.impl.IncognitoGui;
import com.gmail.luxdevpl.minecraftincognito.manager.IUserManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class IncognitoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Tylko gracze mogą używać tej komendy!");
            return true;
        }

        Player player = (Player) commandSender;
        IUserManager userManager = MinecraftIncognito.getInstance().getUserManager();
        UUID playerId = player.getUniqueId();

        IncognitoUser incognitoUser = userManager.getIncognitoUser(playerId).orElseThrow(IllegalAccessError::new);
        new IncognitoGui(incognitoUser).open(player);
        return true;
    }

}
