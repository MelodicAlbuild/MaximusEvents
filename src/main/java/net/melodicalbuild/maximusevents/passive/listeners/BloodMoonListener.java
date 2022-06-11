package net.melodicalbuild.maximusevents.passive.listeners;

import net.melodicalbuild.maximusevents.MaximusEvents;
import net.melodicalbuild.maximusevents.passive.events.BloodMoonEvent;
import net.melodicalbuild.maximusevents.titles.Titles;
import org.bukkit.*;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BloodMoonListener implements Listener{
    private final MaximusEvents plugin;
    public static boolean EventEnabled = false;

    public BloodMoonListener(MaximusEvents plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBloodMoonEvent(BloodMoonEvent event) {
        Server server = Bukkit.getServer();
        Titles.Passive.Bloodmoon.Starting(server);

        World world = Bukkit.getWorld("world");

        assert world != null;
        int object = world.getSpawnLimit(SpawnCategory.MONSTER);
        world.setSpawnLimit(SpawnCategory.MONSTER, 45000);
        world.setDifficulty(Difficulty.HARD);
        world.setTime(18000L);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Titles.Passive.Bloodmoon.Ending(server);
            world.setSpawnLimit(SpawnCategory.MONSTER, object);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

            String message = "&6[&4Broadcast&6]&f The Bloodmoon is Over... For Now...";
            server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        }, 12000);

    }
}
