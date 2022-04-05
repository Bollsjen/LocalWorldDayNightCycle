package dk.bollsjen.localworlddaynightcycle;

import dk.bollsjen.localworlddaynightcycle.Events.SleepHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main _instance;
    public static Main Instance() {return _instance;}

    @Override
    public void onEnable(){
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin enabled");
        getServer().getPluginManager().registerEvents(new SleepHandler(), this);
        _instance = this;
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Plugin disabled");
    }

}
