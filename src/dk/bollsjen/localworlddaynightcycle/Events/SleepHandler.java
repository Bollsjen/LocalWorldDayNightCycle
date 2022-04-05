package dk.bollsjen.localworlddaynightcycle.Events;

import dk.bollsjen.localworlddaynightcycle.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SleepHandler implements Listener {

    private static ArrayList<Player> players = new ArrayList<Player>();

    @EventHandler
    public static void onBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(String.valueOf(player.getSleepTicks()));
        players.add(player);
        Makeday(player);
    }

    @EventHandler
    public static void onBedLeave(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(String.valueOf(player.getSleepTicks()));
        players.remove(player);
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage("Hello world!");
        player.setSleepingIgnored(true);
    }

    public static void Makeday(Player player){
        new BukkitRunnable(){
            @Override
            public void run(){
                player.sendMessage("World!");
                if(players.size() > 0){
                    World world = player.getWorld();
                    ArrayList<Player> localPlayers = new ArrayList<Player>();
                    for (Player _player : players) {
                        if (_player.getWorld() == world) {
                            if (world.getTime() < 1000 || world.getTime() > 12999) {
                                world.setTime(1000);
                                world.setThundering(false);
                                world.setStorm(false);
                            }
                        }
                    }
                    player.sendMessage(world.getName());
                    player.sendMessage("Maked day");
                    players.remove(player);
                }
            }
        }.runTaskLater(Main.Instance(), 80);
    }
}
