package net.melodicalbuild.maximusevents.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.melodicalbuild.maximusevents.MaximusEvents;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.data.type.Bed;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GreatCouncilEntryCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        File particlesFolder = new File(MaximusEvents.dataFolder + "/particles");

        if(args[0].isEmpty()) {
            throw new NullArgumentException("");
        }

        int red1 = 0;
        int red2 = 0;
        int green1 = 0;
        int green2 = 0;
        int blue1 = 0;
        int blue2 = 0;
        float size = 0f;
        Particle particleType = Particle.ELECTRIC_SPARK;

        double x = 0;
        double y = 0;
        double z = 0;

        try {
            Object obj = JsonParser.parseReader(new FileReader(particlesFolder + "/greatcouncil.json"));
            JsonObject jsonObject = (JsonObject) obj;
            JsonObject councilMember = jsonObject.getAsJsonObject(args[0]);
            JsonObject colorSet1 = councilMember.getAsJsonObject("colorSet1");
            JsonObject colorSet2 = councilMember.getAsJsonObject("colorSet2");
            JsonObject position = councilMember.getAsJsonObject("position");

            red1 = colorSet1.getAsJsonPrimitive("red").getAsInt();
            blue1 = colorSet1.getAsJsonPrimitive("blue").getAsInt();
            green1 = colorSet1.getAsJsonPrimitive("green").getAsInt();

            red2 = colorSet2.getAsJsonPrimitive("red").getAsInt();
            blue2 = colorSet2.getAsJsonPrimitive("blue").getAsInt();
            green2 = colorSet2.getAsJsonPrimitive("green").getAsInt();

            size = councilMember.getAsJsonPrimitive("particleSize").getAsFloat();

            particleType = Particle.valueOf(councilMember.getAsJsonPrimitive("particleType").getAsString());

            x = position.getAsJsonPrimitive("x").getAsDouble();
            y = position.getAsJsonPrimitive("y").getAsDouble();
            z = position.getAsJsonPrimitive("z").getAsDouble();

        } catch (Exception e) {
            e.printStackTrace();
        }

        World world = sender.getServer().getWorld("world");
        Particle.DustTransition dustTransition = null;
        Particle.DustOptions dustOptions = null;
        if(particleType == Particle.DUST_COLOR_TRANSITION) {
            dustTransition = new Particle.DustTransition(Color.fromRGB(red1, green1, blue1),
                    Color.fromRGB(red2, green2, blue2), size);
        }

        if(particleType == Particle.REDSTONE) {
            dustOptions = new Particle.DustOptions(Color.fromRGB(red1, green1, blue1), size);
        }
        assert world != null;

        int total = 200;
        final int[] i = {0};
        Particle finalParticleType = particleType;
        Particle.DustOptions finalDustOptions = dustOptions;
        Particle.DustTransition finalDustTransition = dustTransition;
        double finalX = x;
        double finalY = y;
        double finalZ = z;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(sender.getServer().getPluginManager().getPlugin(
                "MaximusEvents")), () -> {
                    if(finalParticleType == Particle.DUST_COLOR_TRANSITION) {
                        world.spawnParticle(finalParticleType, finalX,
                                finalY,
                                finalZ, 50, finalDustTransition);
                        world.spawnParticle(finalParticleType, finalX,
                                finalY + 1,
                                finalZ, 50, finalDustTransition);

                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY,
                                finalZ, 50, finalDustTransition);
                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY + 1,
                                finalZ, 50, finalDustTransition);

                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY,
                                finalZ + 0.5, 50, finalDustTransition);
                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY + 1,
                                finalZ + 0.5, 50, finalDustTransition);

                        world.spawnParticle(finalParticleType, finalX,
                                finalY,
                                finalZ + 0.5, 50, finalDustTransition);
                        world.spawnParticle(finalParticleType, finalX,
                                finalY + 1,
                                finalZ + 0.5, 50, finalDustTransition);
                    } else if(finalParticleType == Particle.REDSTONE) {
                        world.spawnParticle(finalParticleType, finalX,
                                finalY,
                                finalZ, 50, finalDustOptions);
                        world.spawnParticle(finalParticleType, finalX,
                                finalY + 1,
                                finalZ, 50, finalDustOptions);

                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY,
                                finalZ, 50, finalDustOptions);
                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY + 1,
                                finalZ, 50, finalDustOptions);

                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY,
                                finalZ + 0.5, 50, finalDustOptions);
                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY + 1,
                                finalZ + 0.5, 50, finalDustOptions);

                        world.spawnParticle(finalParticleType, finalX,
                                finalY,
                                finalZ + 0.5, 50, finalDustOptions);
                        world.spawnParticle(finalParticleType, finalX,
                                finalY + 1,
                                finalZ + 0.5, 50, finalDustOptions);
                    } else {
                        world.spawnParticle(finalParticleType, finalX,
                                finalY,
                                finalZ, 50);
                        world.spawnParticle(finalParticleType, finalX,
                                finalY + 1,
                                finalZ, 50);

                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY,
                                finalZ, 50);
                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY + 1,
                                finalZ, 50);

                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY,
                                finalZ + 0.5, 50);
                        world.spawnParticle(finalParticleType, finalX + 0.5,
                                finalY + 1,
                                finalZ + 0.5, 50);

                        world.spawnParticle(finalParticleType, finalX,
                                finalY,
                                finalZ + 0.5, 50);
                        world.spawnParticle(finalParticleType, finalX,
                                finalY + 1,
                                finalZ + 0.5, 50);
                    }
                    i[0]++;
                    if(i[0] == total) {
                        Bukkit.getScheduler().cancelTasks(Objects.requireNonNull(sender.getServer().getPluginManager().getPlugin("MaximusEvents")));
                    }
                }, 0L, 1L);
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
            commands.add("center");
            org.bukkit.util.StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
