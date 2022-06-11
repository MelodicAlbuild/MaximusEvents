package net.melodicalbuild.maximusevents;

import net.melodicalbuild.maximuscore.MaximusCore;
import net.melodicalbuild.maximuscore.plugin.Available.EventsPlugin;
import net.melodicalbuild.maximusevents.classes.YamlConfig;
import net.melodicalbuild.maximusevents.commands.GreatCouncilEntryCommand;
import net.melodicalbuild.maximusevents.commands.SpeakCouncilCommand;
import net.melodicalbuild.maximusevents.commands.VoidSpeakCommand;
import net.melodicalbuild.maximusevents.passive.commands.CommandBloodMoon;
import net.melodicalbuild.maximusevents.passive.commands.CommandBossBarDrop;
import net.melodicalbuild.maximusevents.passive.listeners.BloodMoonListener;
import net.melodicalbuild.maximusevents.storyline.commands.AnnounceEventCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public final class MaximusEvents extends JavaPlugin {
    public static JavaPlugin instance;
    public static Plugin maxPlugin = Bukkit.getServer().getPluginManager().getPlugin("MaximusEvents");
    public YamlConfig config = new YamlConfig(this.getDataFolder(), "config.yml");
    public static boolean prerelease = false;
    public static String version = "";

    public static File dataFolder;

    @Override
    public void onEnable() {
        instance = this;

        // Versioning
        PluginDescriptionFile pdf = this.getDescription();
        version = pdf.getVersion();
        prerelease = version.contains("Pre");

        // Startup Logging
        Logger log = Bukkit.getLogger();

        dataFolder = getDataFolder();
        if(!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File extraFile = new File(dataFolder + "/particles");
        if(!extraFile.exists()) {
            extraFile.mkdir();
        }

        MaximusCore.pluginManager.EnablePlugin(new EventsPlugin(version, prerelease));

        //Other
        SetupConfig();

        //Commands
        Objects.requireNonNull(this.getCommand("bloodmoon")).setExecutor(new CommandBloodMoon());
        Objects.requireNonNull(this.getCommand("bossbardrop")).setExecutor(new CommandBossBarDrop());
        Objects.requireNonNull(this.getCommand("announceevent")).setExecutor(new AnnounceEventCommand());
        Objects.requireNonNull(this.getCommand("startcouncil")).setExecutor(new GreatCouncilEntryCommand());
        Objects.requireNonNull(this.getCommand("speakcouncil")).setExecutor(new SpeakCouncilCommand());
        Objects.requireNonNull(this.getCommand("speakvoid")).setExecutor(new VoidSpeakCommand());

        //Listeners
        getServer().getPluginManager().registerEvents(new BloodMoonListener(this), this);
    }

    private void SetupConfig() {
        if(config.getConfig().get("VoidEventPosition") == null) {
            config.getConfig().set("VoidEventPosition", 0);
        }
    }

    @Override
    public void onDisable() {

    }
}
