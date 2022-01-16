package me.magic.plugintwo.events;

import me.magic.plugintwo.PluginTwo;
import me.magic.plugintwo.utils.VaultUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        PersistentDataContainer data = p.getPersistentDataContainer();

        if (!data.has(new NamespacedKey(PluginTwo.getPlugin(), "vault"), PersistentDataType.STRING)){
            data.set(new NamespacedKey(PluginTwo.getPlugin(), "vault"), PersistentDataType.STRING, "");
        }

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){

        Player p = (Player) e.getPlayer();

        if (e.getView().getTitle().equalsIgnoreCase("Your Personal Vault")){

            ArrayList<ItemStack> prunedItems = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(itemStack -> {
                        if (itemStack == null){
                            return false;
                        }
                        return true;
                    })
                    .forEach(itemStack -> prunedItems.add(itemStack));

            System.out.println("size: " + prunedItems.size());

            VaultUtils.storeItems(prunedItems, p);

        }

    }

}
