package me.magic.plugintwo.listeners;

import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowballEvents implements Listener {

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {

        ProjectileSource shooter = e.getEntity().getShooter();
        LivingEntity hitentity = (LivingEntity) e.getHitEntity();
        Random random = new Random();

        if(!(e.getEntity() instanceof Snowball)) return;

        if(hitentity != null) {
            if(shooter instanceof Player) {

                List<Entity> nearest = new ArrayList<>(hitentity.getNearbyEntities(10,10,10));
                List<Entity> finalNearest = new ArrayList<>();

                nearest.forEach(entity -> {
                    if(entity instanceof LivingEntity) finalNearest.add(entity);
                    if(entity instanceof Player) finalNearest.remove(entity);
                });

                for(int i = 0; i < 3; i++) {

                    if(finalNearest.size() > 0){
                        Entity snowball = hitentity.getWorld().spawnEntity(hitentity.getLocation().add(0, 1.2, 0), EntityType.SNOWBALL);
                        Entity target = finalNearest.get(random.nextInt(finalNearest.size()));

                        if(snowball.isDead()) continue;
                        if(!hitentity.hasLineOfSight(target)) continue;
                        if(!(target instanceof LivingEntity) || target == snowball || target == shooter || target == hitentity){
                            snowball.remove();
                            continue;
                        }

                        snowball.setVelocity(target.getLocation().subtract(hitentity.getLocation()).toVector().multiply(0.2));
                        finalNearest.remove(target);

                    }


                }
            }else if(shooter instanceof Snowman) {
                e.getHitEntity().setFireTicks(200);
            }
        }else{

            if(!(shooter instanceof Player)) return;
            if(e.getEntityType().equals(EntityType.SNOWBALL)) {

                World world = e.getEntity().getWorld();
                world.spawnEntity(e.getEntity().getLocation().subtract(0, 0.1, 0), EntityType.SNOWMAN);

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
