package me.magic.plugintwo.listeners;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

// implementing Listener to this class to be able to listen to events 
public class ZombieSnowball implements Listener {

// getting an instance of the plugin 
    Plugin plugin = PluginTwo.getPlugin();

// @Eventhandler so it recognizes that the code below is an event 
    @EventHandler
    // zombieTarget event where the entitytargetevent is used as e
    public void zombieTarget(EntityTargetEvent e) {

// we are checking if the entity that is targetting is a zombie 
        if(e.getEntity() instanceof Zombie) {

// making quick access variables for the target of the zombie and the zombie itself
            Entity zombie = e.getEntity();
            Entity target = e.getTarget();

// checking if the target is a player
            if(target instanceof Player player) {

// creating a list for entities called "nearest" that contains all nearbyentities of the zombie in a 10x10x10 radius xyz
                List<Entity> nearest = zombie.getNearbyEntities(10,10,10);

//creating a simple loop that runs every 10 ticks
                new BukkitRunnable() {

                    public void run() {
                        if(zombie.isDead()) return;

                        // for every entity that is the target in the "nearest" list created before we run the code below
                        for(Entity target : nearest) {
                            if(player.getGameMode() != GameMode.SURVIVAL) return;

                            // creating a snowball entity at the zombies location +1.3y 
                            Entity snowball = zombie.getWorld().spawnEntity(zombie.getLocation().add(0,1.3,0), EntityType.SNOWBALL);
                            //sets the snowballs velocity to targets locations minus zombies location and then make that a vector and multiply by 0.2 for speed
                            snowball.setVelocity(target.getLocation().subtract(zombie.getLocation()).toVector().multiply(0.2));

                        }
                    }
                    // the 10 in here indicates how long until this loop should be played again
                }.runTaskTimer(plugin, 0L, 10);
            }
        }
    }
}
