package me.magic.plugintwo.commands;

import me.magic.plugintwo.utils.VaultUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OpenCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player p = (Player) sender;

            if (args.length > 0){
                if (args[0].equalsIgnoreCase("open")){

                    ArrayList<ItemStack> vaultItems = VaultUtils.getItems(p);

                    Inventory vault = Bukkit.createInventory(p, 54, "Your Personal Vault");

                    vaultItems.stream()
                            .forEach(itemStack -> vault.addItem(itemStack));

                    p.openInventory(vault);

                }
            }

        }



        return true;
    }
}
