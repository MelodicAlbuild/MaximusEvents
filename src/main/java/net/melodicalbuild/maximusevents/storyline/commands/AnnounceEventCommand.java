package net.melodicalbuild.maximusevents.storyline.commands;

import net.melodicalbuild.maximusevents.titles.Titles;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnounceEventCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server server = Bukkit.getServer();
        if(args[0].isEmpty()) {
            throw new NullArgumentException("");
        }
        if(args[2].isEmpty()) {
            throw new NullArgumentException("");
        }
        if(args[3].isEmpty()) {
            throw new NullArgumentException("");
        }

        if(args[0].equalsIgnoreCase("default") && args[1].equalsIgnoreCase("start")) {
            Titles.Storyline.Default.HiddenStart(server, args[2]);
            return true;
        } else if(args[0].equalsIgnoreCase("void") && args[1].equalsIgnoreCase("start")) {
            Titles.Storyline.VoidLine.LineStart(server, args[2], args[3]);
            return true;
        } else if(args[0].equalsIgnoreCase("void") && args[1].equalsIgnoreCase("end")) {
            Titles.Storyline.VoidLine.LineEnd(server);
            return true;
        }

        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        List<String> commands1 = new ArrayList<>();

        if(args.length == 1) {
            commands.add("default");
            commands.add("void");
            org.bukkit.util.StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        if(args.length == 2) {
            commands1.add("start");
            commands1.add("end");
            org.bukkit.util.StringUtil.copyPartialMatches(args[1], commands1, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
