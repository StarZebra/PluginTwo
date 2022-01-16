package me.magic.plugintwo.commands;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SmeltCountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            Block b = p.getTargetBlockExact(5);

            if (b.getState() instanceof TileState) {

                TileState tileState = (TileState) b.getState();

                PersistentDataContainer container = tileState.getPersistentDataContainer();

                if(container.has(new NamespacedKey(PluginTwo.getPlugin(), "smeltcount"), PersistentDataType.INTEGER)) {

                    int currentAmount = container.get(new NamespacedKey(PluginTwo.getPlugin(), "smeltcount"), PersistentDataType.INTEGER);

                    p.sendMessage("This block has smelted: " + currentAmount + " items.");

                }else{

                    p.sendMessage("This block has not smelted any items");

                }

            }else{
                p.sendMessage("Invalid tilestate");
            }

        }

        return true;
    }
}
