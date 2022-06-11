package net.melodicalbuild.maximusevents.storyline.commands;

import net.melodicalbuild.maximusevents.MaximusEvents;
import net.melodicalbuild.maximusevents.storyline.lines.VoidLine;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartStorylineCommand implements TabExecutor {
    MaximusEvents events;

    public StartStorylineCommand(MaximusEvents events) {
        this.events = events;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].isEmpty()) {
            throw new NullArgumentException("");
        } else {
            switch(args[0]) {
                case "void":
                    VoidLine.StartEventCountdown(events);
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(args.length == 1) {
            commands.add("void");
            org.bukkit.util.StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
