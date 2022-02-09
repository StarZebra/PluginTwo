package me.magic.plugintwo.listeners;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GoldOreBreak implements Listener {

    Plugin plugin = PluginTwo.getPlugin();

    @EventHandler
    public void onGoldMine(BlockBreakEvent e){

        if(e.getBlock().getType().equals(Material.GOLD_ORE)) {

            Player player = e.getPlayer();

            e.setCancelled(true);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
            e.getBlock().setType(Material.STONE);

            new BukkitRunnable(){
                int i = 0;
                public void run(){
                    if(!e.getBlock().getType().isAir()){
                        if(i < 1) {
                            e.getBlock().setType(Material.GOLD_ORE);
                        }
                        i++;
                    }
                }
            }.runTaskTimer(plugin, 20L, 0);

        }
    }

}
