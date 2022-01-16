package me.magic.plugintwo.listeners;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();

        PersistentDataContainer data = p.getPersistentDataContainer();

        int deathcount = data.get(new NamespacedKey(PluginTwo.getPlugin(), "deaths"), PersistentDataType.INTEGER);

        deathcount++;

        p.sendMessage(ChatColor.RED + "You have died! " + ChatColor.GRAY + "(" + deathcount + ")");

        data.set(new NamespacedKey(PluginTwo.getPlugin(), "deaths"), PersistentDataType.INTEGER, deathcount);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if(!p.getPersistentDataContainer().has(new NamespacedKey(PluginTwo.getPlugin(), "deaths"), PersistentDataType.INTEGER)) {
            p.getPersistentDataContainer().set(new NamespacedKey(PluginTwo.getPlugin(), "deaths"), PersistentDataType.INTEGER, 0);
        }

    }

}
