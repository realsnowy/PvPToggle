package tf.justdisablevac.pvptoggle.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tf.justdisablevac.pvptoggle.main;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class pvp implements CommandExecutor {

    HashMap<String, Long> cooldownManager = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();
            Boolean pvpState = main.plugin.config.getBoolean(playerName);

            long timeLeft = System.currentTimeMillis() - cooldownManager.getOrDefault(playerName, 0L); //get the amount of milliseconds that have passed since the feature was last used.
            int DEFAULT_COOLDOWN = 60; //command cooldown in seconds

            if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= DEFAULT_COOLDOWN) {
                cooldownManager.put(playerName, System.currentTimeMillis());
                if(pvpState) {
                    main.plugin.config.set(playerName, false);
                    main.plugin.saveConfig();
                    player.sendTitle("§6PvPToggle", "Your pvp has been disabled", 20, 40, 20);
                } else {
                    main.plugin.config.set(playerName, true);
                    main.plugin.saveConfig();
                    player.sendTitle("§6PvPToggle", "Your pvp has been enabled", 20, 40, 20);
                }
            } else {
                player.sendMessage("§c You have to wait before you can use this feature again.");
            }
        } return false;
    }
}
