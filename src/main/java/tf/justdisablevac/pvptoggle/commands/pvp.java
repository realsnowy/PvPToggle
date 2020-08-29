package tf.justdisablevac.pvptoggle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tf.justdisablevac.pvptoggle.main;

public class pvp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerName = player.getName();
        Boolean pvpState = main.plugin.config.getBoolean(playerName);

        if(pvpState) {
                main.plugin.config.set(playerName, false);
                main.plugin.saveConfig();
                player.sendTitle("§6PvPToggle", "Your pvp has been disabled", 20, 40, 20);
        } else {
                main.plugin.config.set(playerName, true);
                main.plugin.saveConfig();
            player.sendTitle("§6PvPToggle", "Your pvp has been enabled", 20, 40, 20);
        } return false;
    }
}
