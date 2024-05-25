package com.guedosha.simplegodmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Simplegodmode extends JavaPlugin implements Listener {

    private static Simplegodmode plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[SimpleGodmode] &fSuccessfully Loaded Config"));

        getCommand("god").setExecutor(new GodCommand());
        getCommand("reload").setExecutor(new ReloadHandler());
    }
}
