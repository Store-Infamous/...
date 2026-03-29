package com.infamous.organsmp;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player victim = e.getEntity();
        Player killer = victim.getKiller();

        if (killer == null || killer.equals(victim)) return;

        // Anti-Alt System
        if (killer.getAddress().getAddress().equals(victim.getAddress().getAddress())) {
            killer.sendMessage(ChatColor.RED + "You cannot steal organs from your own IP!");
            return;
        }

        String targetOrgan = null;
        int maxStr = -1;

        // Find Strongest Organ
        for (String type : organTypes) {
            int str = plugin.getStrength(victim, type);
            if (str > maxStr) {
                maxStr = str;
                targetOrgan = type;
            }
        }

        int stolenAmount;
        if (maxStr <= 0) {
            // New player victim: Killer gets 10 strength in a random organ
            targetOrgan = organTypes[plugin.getRandom().nextInt(organTypes.length)];
            stolenAmount = 10;
        } else {
            // Victim had power: Killer steals it all
            stolenAmount = maxStr;
            plugin.setStrength(victim, targetOrgan, 0);
        }

        int killerCurrent = plugin.getStrength(killer, targetOrgan);
        plugin.setStrength(killer, targetOrgan, killerCurrent + stolenAmount);

        killer.sendMessage(ChatColor.GREEN + "✔ You ripped out " + victim.getName() + "'s " + targetOrgan + " (+" + stolenAmount + " STR)");
        killer.playSound(killer.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1f, 1f);
        victim.sendMessage(ChatColor.RED + "☠ Your " + targetOrgan + " was stolen by " + killer.getName() + "!");
    }
}
