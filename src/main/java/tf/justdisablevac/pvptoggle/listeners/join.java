package tf.justdisablevac.pvptoggle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tf.justdisablevac.pvptoggle.main;

public class join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        Boolean hasConfig = main.plugin.config.isBoolean(playerName);

        if(!hasConfig) {
            main.plugin.config.set(playerName, true);
            main.plugin.saveConfig();
        }
    }
}
