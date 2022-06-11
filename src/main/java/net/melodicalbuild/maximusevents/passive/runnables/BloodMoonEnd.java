package net.melodicalbuild.maximusevents.passive.runnables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BloodMoonEnd extends BukkitRunnable {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MaximusEvents");
    @Override
    public void run() {
        Bukkit.getServer().broadcastMessage("Welcome to Bukkit! Remember to read the documentation!");
        Bukkit.getScheduler().cancelTasks(plugin);
    }
}
