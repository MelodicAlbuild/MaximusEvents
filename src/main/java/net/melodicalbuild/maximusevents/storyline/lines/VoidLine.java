package net.melodicalbuild.maximusevents.storyline.lines;

import net.melodicalbuild.maximusevents.MaximusEvents;
import net.melodicalbuild.maximusevents.storyline.classes.MaxEvent;
import net.melodicalbuild.maximusevents.titles.Titles;

import java.util.List;

import org.bukkit.Server;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.Plugin;

public class VoidLine {
    private static MaximusEvents events;
    private static List<MaxEvent> EventList;
    private static int LinePos;

    public static void FinishEvent() {
        if(LinePos != EventList.size()) {
            LinePos++;
            events.config.getConfig().set("VoidEventPosition", LinePos);
        } else {
            EndStoryline();
        }
    }

    private static void EventCountdown(String startTime) {
        Server server = Bukkit.getServer();
        Titles.Storyline.VoidLine.LineStart(server, startTime, "King");
    }

    public static int i = 0;

    public static void StartEventCountdown(MaximusEvents eventsN) {
        events = eventsN;
        events.config.getConfig().getInt("VoidEventPosition");

        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MaximusEvents");
        assert plugin != null;

        int[] times = {30, 15, 5, 1};

        new BukkitRunnable() {
            @Override
            public void run() {
                EventCountdown("" + times[i]);
                if(i == times.length - 1) {
                    EventList.get(LinePos).EnableEvent();
                } else {
                    i++;
                    int tickTime = times[i] * 60 * 20;
                    this.runTaskLater(plugin, tickTime);
                }
            }
        }.runTaskLater(plugin, 0);
    }

    private static void EndStoryline() {
        Server server = Bukkit.getServer();
        Titles.Storyline.VoidLine.LineEnd(server);
    }
}
