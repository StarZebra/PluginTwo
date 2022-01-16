package me.magic.plugintwo.events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
public class BlockPlace implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Block block = e.getBlock();

        e.getPlayer().sendMessage(block.toString());

    }
}
