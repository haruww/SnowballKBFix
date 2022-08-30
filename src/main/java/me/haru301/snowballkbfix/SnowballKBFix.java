package me.haru301.snowballkbfix;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SnowballKBFix extends JavaPlugin
{

    public static SnowballKBFix plugin;
    public static FileConfiguration config;

    @Override
    public void onEnable()
    {
        SnowballKBFix.plugin = this;
        SnowballKBFix.config = this.getConfig();
        SnowballKBFix.config.options().copyDefaults(true);

        getServer().getPluginManager().registerEvents(new PluginEventHandler(), this);

        this.saveConfig();
        this.getCommand("reloadconfig").setExecutor(this);

        this.getLogger().info(this.getDescription().getName() + " enabled");
    }

    @Override
    public void onDisable()
    {
        this.getLogger().info(this.getDescription().getName() + " disabled");
    }

    public void reloadConfiguration() {
        this.reloadConfig();
        SnowballKBFix.config = this.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            reloadConfiguration();
            sender.sendMessage(ChatColor.GREEN + "[SnowballKB] Config has been reloaded!");
        } catch(NullPointerException e) {
            sender.sendMessage(ChatColor.DARK_RED + "[SnowballKB] Error! Check console for more details.");
        }
        return true;
    }
}
