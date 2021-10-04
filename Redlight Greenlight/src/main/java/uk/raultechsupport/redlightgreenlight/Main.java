package uk.raultechsupport.redlightgreenlight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Main extends JavaPlugin implements Listener {

    boolean rlglenabled = false;

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Hello Redlight Greenlight!");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Goodnight Redlight Greenlight!");

        if (!Bukkit.getWorlds().isEmpty()) {
            getLogger().warning("======================");
            getLogger().warning("    RELOAD DETECTED   ");
            getLogger().warning("    Why do you hate   ");
            getLogger().warning("       yourself?      ");
            getLogger().warning("======================");
            getLogger().warning("       I REFUSE       ");
            getLogger().warning("     TO ACCEPT THIS   ");
            getLogger().warning("      SAY GOODBYE     ");
            getLogger().warning("======================");
            Bukkit.shutdown();
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("redlight")) {
            rlglenabled = true;
            Bukkit.broadcastMessage(ChatColor.RED + "Red Light!" );
        }
        else if (command.getName().equalsIgnoreCase("greenlight")) {
            rlglenabled = false;
            Bukkit.broadcastMessage(ChatColor.GREEN + "Green Light!" );
        }
        else if (command.getName().equalsIgnoreCase("about")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GOLD + "Redlight Greenlight plugin developed by RaulTechSupport!");
            }
        }

        return true;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (rlglenabled) {
            if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ())
                return; //The player hasn't moved
            e.getPlayer().sendMessage(ChatColor.RED + "You moved and have been eliminated!");
            e.getPlayer().setHealth(0.0);
        }
    }
}
