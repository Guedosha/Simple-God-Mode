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
            if (args.length == 0) {
                if (p.isInvulnerable()) {
                    p.sendMessage(ChatColor.GOLD + "You are no longer in god mode");
                    p.setInvulnerable(false);
                } else {
                    p.sendMessage(ChatColor.GOLD + "You are now in god mode");
                    p.setInvulnerable(true);
                    p.setHealth(p.getMaxHealth());
                    p.setFoodLevel(20);
                }
            } else if (args.length == 1) {
                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);

                //Checks if the target is online and deals with them accordingly otherwise it will return false
                if (target == null) {
                    p.sendMessage(ChatColor.GOLD + "That Player Isn't Online");
                } else {
                    //Checks if the player is in GodMode and deals with them accordingly
                    if (target.isInvulnerable()) {
                        p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " is no longer in god mode");
                        target.setInvulnerable(false);
                        target.sendMessage(ChatColor.GOLD + "You are no longer in god mode");
                    } else {
                        p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " is now in god mode");
                        target.setInvulnerable(true);
                        target.sendMessage(ChatColor.GOLD + "You are now in god mode");
                        target.setHealth(target.getMaxHealth());
                        target.setFoodLevel(20);
                    }
                }
            }
        } else {
            if (args.length == 0) {
                plugin.getLogger().info("Usage: /godmode ; /godmode <target>");
            } else if (args.length == 1) {
                String playerNameConsole = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerNameConsole);

                //checks if the player is online if not it will check their godmode status
                if (t == null) {
                    plugin.getLogger().info("That player is not online");
                } else if (t.isInvulnerable()){
                    t.setInvulnerable(false);
                    t.sendMessage(ChatColor.GOLD + "You are no longer in god mode");
                } else {
                    t.setInvulnerable(true);
                    t.sendMessage(ChatColor.GOLD + "You are now in god mode");
                    t.setHealth(t.getMaxHealth());
                    t.setFoodLevel(20);
                }
            } else {
                plugin.getLogger().info("Usage: /godmode ; /godmode <target>");
            }
        }
        return true;
    }
}
