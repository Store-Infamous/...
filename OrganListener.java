package com.infamous.organsmp;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OrganListener implements Listener {
    private final OrganSMP plugin;

    public OrganListener(OrganSMP plugin) {
        this.plugin = plugin;
killer.getAddress().getAddress().equals(victim.getAddress().getAddress())) return;

        OrganManager mgr = plugin.getOrgans();
        OrganType stolenType = mgr.getStrongest(victim);
        int amount;

        if (stolenType == null) {
            stolenType = mgr.getRandom();
            amount = 10;
        } else {
            amount = mgr.getStrength(victim, stolenType);
            mgr.setStrength(victim, stolenType, 0); // Reset victim
        }

        mgr.setStrength(killer, stolenType, mgr.getStrength(killer, stolenType) + amount);
        
        killer.sendMessage("§a§l+ STOLE " + amount + " " + stolenType.name + " strength!");
        killer.playSound(killer.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
        victim.sendMessage("§c§l- LOST your " + stolenType.name + "!");
    }
}
