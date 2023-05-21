package com.guedosha.simplegodmode;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Simplegodmode extends JavaPlugin implements Listener {

    private static Simplegodmode plugin;

    @Override
    public void onEnable() {
        getCommand("god").setExecutor( new GodCommand());
        plugin = this;
    }

    public static Simplegodmode getPlugin() {
        return plugin;
    }

}
