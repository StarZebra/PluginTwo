package me.magic.plugintwo.listeners;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ZombieSnowball implements Listener {

    Plugin plugin = PluginTwo.getPlugin();

    @EventHandler
    public void zombieTarget(EntityTargetEvent e) {

        if(e.getEntity() instanceof Zombie) {

            Entity zombie = e.getEntity();
            Entity target = e.getTarget();

            if(target instanceof Player) {

                new BukkitRunnable() {
                    public void run() {

                        if(!zombie.isDead()){
                            if(!((Player) target).getAllowFlight()) {

                                Entity snowball = zombie.getWorld().spawnEntity(zombie.getLocation().add(0,1.3,0), EntityType.SNOWBALL);
                                snowball.setVelocity(target.getLocation().add(0,1,0).subtract(snowball.getLocation()).toVector().multiply(0.23));

                            }else{
                                cancel();
                            }
                        }else {
                            cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 10);
            }
        }
    }
}
