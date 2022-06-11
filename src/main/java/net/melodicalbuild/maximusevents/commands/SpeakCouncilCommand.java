package net.melodicalbuild.maximusevents.commands;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeakCouncilCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].isEmpty() || args[1].isEmpty()) {
            throw new NullArgumentException("");
        }

        StringBuilder msg = new StringBuilder();

        for(int r = 0; r < args.length; r++) {
            if(r == 0) continue;
            msg.append(args[r]).append(" ");
        }

        Server server = sender.getServer();

        switch(args[0]) {
            case "morgus":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &6Morgus&f: " + msg));
                break;
            case "skerathwil":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &aSkerathwil&f: " + msg));
                break;
            case "darintre":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &aD&ba&cr&di&en&ft&9r&0e&f: " + msg));
                break;
            case "fis-silpin":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &cFis-silpin&f: " + msg));
                break;
            case "grimdira":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &0Grimdira&f: " + msg));
                break;
            case "vol-mor":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &dVol-mor&f: " + msg));
                break;
            case "winterstorm":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &bWinterstorm&f: " + msg));
                break;
            case "goldmoor":
                server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5[&9Great Council&5] &eGoldmoor&f: " + msg));
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(args.length == 1) {
            commands.add("morgus");
            commands.add("skerathwil");
            commands.add("darintre");
            commands.add("fis-silpin");
            commands.add("grimdira");
            commands.add("vol-mor");
            commands.add("winterstorm");
            commands.add("goldmoor");
            org.bukkit.util.StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
