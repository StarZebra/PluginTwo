package me.magic.plugintwo.events;

import me.magic.plugintwo.PluginTwo;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

public class SnowballEvents implements Listener {

    Plugin plugin = PluginTwo.getPlugin();

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {

        ProjectileSource shooter = e.getEntity().getShooter();
        LivingEntity hitentity = (LivingEntity) e.getHitEntity();


        if(hitentity != null) {
            if(shooter instanceof Player) {

                new BukkitRunnable() {
                    int i = 0;

                    public void run() {
                        if(i < 3) {

                            Entity snowball = hitentity.getWorld().spawnEntity(hitentity.getLocation().add(0,1.2,0), EntityType.SNOWBALL);

                            for(Entity entity : snowball.getNearbyEntities(10,10,10)) {
                                if(!snowball.isDead()) {
                                    if(snowball.getLocation().distance(entity.getLocation() ) <= 10) {
                                        if(entity != snowball && entity != shooter && entity != hitentity) {
                                            if(entity instanceof LivingEntity) {
                                                LivingEntity livingentity = (LivingEntity) entity;

                                                snowball.setVelocity(livingentity.getLocation().add(0,1,0).subtract(hitentity.getLocation()).toVector().multiply(0.2));

                                            }
                                        }
                                    }
                                }
                            }
                            i++;
                        }else{
                            cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 0);
            }else if(shooter instanceof Snowman) {
                e.getHitEntity().setFireTicks(200);
            }

        }else{

            if(shooter instanceof Player) {

                if(e.getEntityType().equals(EntityType.SNOWBALL)) {

                    World world = e.getEntity().getWorld();
                    world.spawnEntity(e.getEntity().getLocation().subtract(0, 0.1, 0), EntityType.SNOWMAN);

                }
            }
        }
    }

    @EventHandler
    public void onSnowball(ProjectileLaunchEvent e) {

        ProjectileSource shooter = e.getEntity().getShooter();

        if(shooter instanceof Snowman) {

            if(e.getEntityType().equals(EntityType.SNOWBALL)) {
                e.getEntity().setVisualFire(true);

            }
        }
    }
}
