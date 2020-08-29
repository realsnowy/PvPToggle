package tf.justdisablevac.pvptoggle.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import tf.justdisablevac.pvptoggle.main;

public class damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Boolean damagerState = main.plugin.config.getBoolean(damager.getName());
            Player attacked = (Player) event.getEntity();
            Boolean attackedState = main.plugin.config.getBoolean(attacked.getName());

            if(!damagerState) {
                event.setCancelled(true);
                damager.sendTitle("§6PvPToggle", "Your pvp is disabled", 20, 40, 20);
                damager.playSound(damager.getLocation(), Sound.ENTITY_PIG_AMBIENT, 10, 1);
            } else if(attackedState != null && !attackedState) {
                event.setCancelled(true);
                damager.sendTitle("§6PvPToggle", attacked.getName() + " has pvp disabled", 20, 40, 20);
                damager.playSound(damager.getLocation(), Sound.ENTITY_PIG_AMBIENT, 10, 1);
            }
        } else if(event.getDamager() instanceof Projectile) {
            Projectile arrow = (Projectile) event.getDamager();
            if(arrow.getShooter() instanceof Player) {
                if(event.getEntity() instanceof Player) {
                    Player damager = (Player) arrow.getShooter();
                    Boolean damagerState = main.plugin.config.getBoolean(damager.getName());
                    Player attacked = (Player) event.getEntity();
                    Boolean attackedState = main.plugin.config.getBoolean(attacked.getName());
                    if(damager == attacked) {
                        return;
                    }
                    if(!damagerState) {
                        event.setCancelled(true);
                        damager.sendTitle("§6PvPToggle", "Your pvp is disabled");
                        damager.playSound(damager.getLocation(), Sound.ENTITY_PIG_AMBIENT, 10, 1);
                    } else if(attackedState != null && !attackedState) {
                        event.setCancelled(true);
                        damager.sendTitle("§6PvPToggle", attacked.getName() + " has pvp disabled", 20, 40, 20);
                        damager.playSound(damager.getLocation(), Sound.ENTITY_PIG_AMBIENT, 10, 1);
                    }
                }
            }
        }
    }
}
