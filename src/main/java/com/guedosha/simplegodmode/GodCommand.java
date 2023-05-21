package com.guedosha.simplegodmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GodCommand implements CommandExecutor {

    Plugin plugin = Simplegodmode.getPlugin(Simplegodmode.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (!p.hasPermission("Simplegodmode.godmode")){
                p.sendMessage(ChatColor.RED + "You don't have permission to use that command");
                return true;
            }

            if (args.length == 0) {
                toggleGod(null, p);
            } else if (args.length == 1) {
                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "That Player Isn't Online");
                } else {
                    toggleGod(p, target);
                }
            }
        } else {
            if (args.length == 0) {
                plugin.getLogger().info(ChatColor.RED + "Usage: /godmode or /godmode <target>");
            } else if (args.length == 1) {
                String playerNameConsole = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerNameConsole);
                if (t == null) {
                    plugin.getLogger().info(ChatColor.RED + "That player is not online");
                } else {
                    toggleGod(null, t);
                }
            } else {
                plugin.getLogger().info(ChatColor.RED + "Usage: /godmode or /godmode <target>");
            }
        }
        return true;
    }

    private void toggleGod(Player sender, Player target) {
        if (target.isInvulnerable()){
            target.setInvulnerable(false);
            target.sendMessage(ChatColor.GOLD + "Godmode" + ChatColor.RED + ChatColor.BOLD + " Disabled");
            if (sender != null) {
                sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + target.getName() + ChatColor.GOLD +" is no longer in god mode");
            } else {
                plugin.getLogger().info(ChatColor.YELLOW + "" + ChatColor.BOLD + target.getName() + ChatColor.GOLD + " is no longer in god mode");
            }
        } else {
            target.setInvulnerable(true);
            target.sendMessage(ChatColor.GOLD + "Godmode" + ChatColor.GREEN + ChatColor.BOLD + " Enabled");
            target.setHealth(target.getMaxHealth());
            target.setFoodLevel(20);
            target.setSaturation(5);
            if (sender != null) {
                sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + target.getName() + ChatColor.GOLD +" is now in god mode");
            } else {
                plugin.getLogger().info(ChatColor.YELLOW + "" + ChatColor.BOLD + target.getName() + ChatColor.GOLD + " is now in god mode");
            }
        }
    }
}
