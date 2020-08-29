package tf.justdisablevac.pvptoggle;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tf.justdisablevac.pvptoggle.commands.pvp;
import tf.justdisablevac.pvptoggle.listeners.damage;
import tf.justdisablevac.pvptoggle.listeners.join;

public final class main extends JavaPlugin {

    public FileConfiguration config;
    public static main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.config = getConfig();
        getServer().getPluginManager().registerEvents(new join(), this);
        getServer().getPluginManager().registerEvents(new damage(), this);
        getCommand("togglepvp").setExecutor(new pvp());
    }
}
