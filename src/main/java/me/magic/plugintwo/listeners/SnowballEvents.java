package me.magic.plugintwo.listeners;

import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.List;

public class SnowballEvents implements Listener {

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {

        ProjectileSource shooter = e.getEntity().getShooter();
        LivingEntity hitentity = (LivingEntity) e.getHitEntity();


        if(hitentity != null) {
            if(shooter instanceof Player) {

                for(int i = 0; i < 3; i++){

                    Entity snowball = hitentity.getWorld().spawnEntity(hitentity.getLocation().add(0,1.2,0), EntityType.SNOWBALL);
                    List<Entity> nearest = snowball.getNearbyEntities(10,10,10);

                    for(Entity target : nearest) {
                        if(snowball.isDead()) return;
                        if(!hitentity.hasLineOfSight(target)) return;
                        if(target instanceof LivingEntity && target != snowball && target != shooter && target != hitentity) {

                            snowball.setVelocity(target.getLocation().subtract(hitentity.getLocation()).toVector().multiply(0.2));

                        }
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
