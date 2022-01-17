package me.magic.plugintwo.listeners;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CookListener implements Listener {

    //Completely breaks furnaces making them unusable

    @EventHandler
    public void smeltItem(FurnaceSmeltEvent e) {

        Block block = e.getBlock();
        BlockState blockState = block.getState();

        if(blockState instanceof TileState) {

            TileState tileState = (TileState) blockState;

            PersistentDataContainer container = tileState.getPersistentDataContainer();

            if(container.has(new NamespacedKey(PluginTwo.getPlugin(), "smeltcount"), PersistentDataType.INTEGER)) {

                int currentAmount = container.get(new NamespacedKey(PluginTwo.getPlugin(), "smeltcount"), PersistentDataType.INTEGER);

                container.set(new NamespacedKey(PluginTwo.getPlugin(), "smeltcount"), PersistentDataType.INTEGER, currentAmount + 1);

            }else{

                container.set(new NamespacedKey(PluginTwo.getPlugin(), "smeltcount"), PersistentDataType.INTEGER, 1);

            }

            tileState.update();

        }
    }

}
