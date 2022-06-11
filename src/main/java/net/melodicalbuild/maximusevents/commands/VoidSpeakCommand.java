package net.melodicalbuild.maximusevents.commands;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoidSpeakCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].isEmpty()) {
            throw new NullArgumentException("");
        }

        StringBuilder msg = new StringBuilder();

        for(int r = 0; r < args.length; r++) {
            if(r == 0) continue;
            msg.append(args[r]).append(" ");
        }

        Server server = sender.getServer();
        server.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                "&5[&dThe " + args[0] + " Void&5] Void&f: " + msg));

        return true;
    }
}
