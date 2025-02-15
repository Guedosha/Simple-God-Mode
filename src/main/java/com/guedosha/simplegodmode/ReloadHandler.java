package com.guedosha.simplegodmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ReloadHandler implements CommandExecutor {

    Plugin plugin = Simplegodmode.getPlugin(Simplegodmode.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String reloaded = ChatColor.translateAlternateColorCodes('&', "&a[SimpleGodMode] &fSuccessfully Reloaded Config");

        if (sender instanceof Player p) {
            if (!p.hasPermission("simplegodmode.reload")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.no-permission").replace("%target%", p.getName()).replace("%caster", p.getName())));
                return true;
            }
            reloadConfig();
            Bukkit.getConsoleSender().sendMessage(reloaded);
            p.sendMessage(reloaded);
        } else if (sender instanceof ConsoleCommandSender){
            reloadConfig();
            Bukkit.getConsoleSender().sendMessage(reloaded);
        }
        return true;
    }
    private Configuration config = plugin.getConfig();

    public void reloadConfig(){
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public Configuration getConfig(){
        return config;
    }
}
