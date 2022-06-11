package net.melodicalbuild.maximusevents.passive.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandBossBarDrop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MaximusEvents");
        assert plugin != null;
        final double[] value = {1};
        if(args[0] != null && args[1] != null) {
            double str1 = Double.parseDouble(args[1]);
            BossBar bBar = Bukkit.getBossBar(NamespacedKey.minecraft(args[0]));
            new BukkitRunnable() {
                @Override
                public void run() {
                    assert bBar != null;
                    if(value[0] > 0.1) {
                        bBar.setProgress(value[0]);
                        value[0] = value[0] - str1;
                    } else {
                        bBar.removeAll();
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
            return true;
        } else if(args[0] == null) {
            String message = "&3[&aMaximus Events&3]&f: &4You Forgot the Bossbar ID that you wish to Increment";
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        } else {
            String message = "&3[&aMaximus Events&3]&f: &4You Forgot the amount at which the Bossbar Should Increment by";
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
        return false;
    }
}
