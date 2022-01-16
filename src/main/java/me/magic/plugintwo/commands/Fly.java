package me.magic.plugintwo.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(player.getAllowFlight()) {

                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(ChatColor.YELLOW + "Fly mode disabled.");

            }else{

                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage(ChatColor.YELLOW + "Fly mode enabled.");

            }

        }
        return true;
    }
}
