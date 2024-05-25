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

public class GodCommand implements CommandExecutor {

    Plugin plugin = Simplegodmode.getPlugin(Simplegodmode.class);
    ConsoleCommandSender logger = Bukkit.getConsoleSender();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Configuration config = new ReloadHandler().getConfig();
        if (sender instanceof Player p) {
            if (!p.hasPermission("simplegodmode.use")) { p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-permission").replace("%caster%", p.getName()).replace("%target%", ""))); return true; }
            if (args.length == 0) {
                toggleGod(p, false);
            } else if (args.length == 1) {
                String target = args[0];
                Player t = Bukkit.getPlayerExact(target);
                if (t==null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-offline").replace("%target%", target).replace("%caster%", p.getName())));
                } else {
                    toggleGod(p, t);
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage").replace("%caster%", p.getName()).replace("%target%", p.getName())));
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                String target = args[0];
                Player t = Bukkit.getPlayerExact(target);
                if (t==null) logger.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-offline").replace("%target%", target).replace("%caster%", "Console")));
                else toggleGod(t, true);

            } else logger.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage").replace("%caster%", "").replace("%target%", "Console")));
        }
        return true;
    }

    public void toggleGod (Player caster, Player target) {
        Configuration config = new ReloadHandler().getConfig();
        String targetEnabled = ChatColor.translateAlternateColorCodes( '&',config.getString("messages.target-enabled")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        String casterEnabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-enabled")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        String targetDisabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-disabled")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        String casterDisabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-disabled")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        if (target.isInvulnerable()) {
            target.setInvulnerable(false);
            target.sendMessage(targetDisabled);
            if (!caster.getName().equals(target.getName())) caster.sendMessage(casterDisabled);
        } else {
            target.setInvulnerable(true);
            if (config.getBoolean("feed-on-godmode")) target.setFoodLevel(20);
            if (config.getBoolean("heal-on-godmode")) target.setHealth(target.getMaxHealth());
            int sat = config.getInt("saturation-amount");
            if (sat >= 1 && sat <= 20) {
                target.setSaturation(sat);
            }
            target.sendMessage(targetEnabled);
            if (!caster.getName().equals(target.getName())) caster.sendMessage(casterEnabled);
        }
    }

    public void toggleGod (Player target, boolean consoleSender) {
        Configuration config = new ReloadHandler().getConfig();
        if (consoleSender) {
            String targetEnabled = ChatColor.translateAlternateColorCodes( '&',config.getString("messages.target-enabled")).replace("%target%", target.getName()).replace("%caster%", "Console");
            String casterEnabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-enabled")).replace("%target%", target.getName()).replace("%caster%", "Console");
            String targetDisabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-disabled")).replace("%target%", target.getName()).replace("%caster%", "Console");
            String casterDisabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-disabled")).replace("%target%", target.getName()).replace("%caster%", "Console");
            if (target.isInvulnerable()) {
                target.setInvulnerable(false);
                target.sendMessage(targetDisabled);
                logger.sendMessage(casterDisabled);
            } else {
                target.setInvulnerable(true);
                if (config.getBoolean("feed-on-godmode")) target.setFoodLevel(20);
                if (config.getBoolean("heal-on-godmode")) target.setHealth(target.getMaxHealth());
                if (config.getInt("saturation-amount") >= 1 && config.getInt("saturation-amount") <= 20) {
                    target.setSaturation(config.getInt("saturation-amount"));
                }
                target.sendMessage(targetEnabled);
                logger.sendMessage(casterEnabled);
            }
        } else {
            String targetEnabled = ChatColor.translateAlternateColorCodes( '&',config.getString("messages.target-enabled")).replace("%target%", target.getName()).replace("%caster%", target.getName());
            String targetDisabled = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-disabled")).replace("%target%", target.getName()).replace("%caster%", target.getName());
            if (target.isInvulnerable()) {
                target.setInvulnerable(false);
                target.sendMessage(targetDisabled);
            } else {
                target.setInvulnerable(true);
                if (config.getBoolean("feed-on-godmode")) target.setFoodLevel(20);
                if (config.getBoolean("heal-on-godmode")) target.setHealth(target.getMaxHealth());
                if (config.getInt("saturation-amount") >= 1 && config.getInt("saturation-amount") <= 20) {
                    target.setSaturation(config.getInt("saturation-amount"));
                }
                target.sendMessage(targetEnabled);
            }
        }
    }
}
