package com.infamous.organsmp;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OrganListener implements Listener {

    private final OrganSMP plugin;

    public OrganListener(OrganSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player victim = e.getEntity();
        Player killer = victim.getKiller();

        if (killer == null || killer.equals(victim)) return;

        OrganManager mgr = plugin.getManager();

        OrganType type = mgr.getStrongest(victim);
        int amount;

        if (type == null) {
            type = mgr.getRandom();
            amount = 10;
        } else {
            amount = mgr.getStrength(victim, type);
            mgr.setStrength(victim, type, 0);
        }

        int current = mgr.getStrength(killer, type);
        mgr.setStrength(killer, type, current + amount);

        killer.sendMessage(ChatColor.GREEN + "Stole " + amount + " " + type.name);
        victim.sendMessage(ChatColor.RED + "Lost your " + type.name);

        killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }
}
