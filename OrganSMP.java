package com.infamous.organsmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OrganSMP extends JavaPlugin {
    private OrganManager organManager;

    @Override
    public void onEnable() {
        this.organManager = new OrganManager(this);
        Bukkit.getPluginManager().registerEvents(new OrganListener(this), this);
        
        // Effects Loop (Every 5 seconds)
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (org.bukkit.entity.Player p : Bukkit.getOnlinePlayers()) {
                applyBuffs(p);
            }
        }, 0L, 100L);
    }

    private void applyBuffs(org.bukkit.entity.Player p) {
        if.LEGS) >= 20) 
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 1));
        if (organManager.getStrength(p, OrganType.ARMS) >= 30) 
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 0));
        if (organManager.getStrength(p, OrganType.EYES) >= 10) 
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
    }

    public OrganManager getOrgans() { return organManager; }
}
