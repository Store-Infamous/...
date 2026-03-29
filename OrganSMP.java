package com.infamous.organsmp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.*;

public class OrganSMP extends JavaPlugin {

    private OrganManager manager;

    @Override
    public void onEnable() {

        manager = new OrganManager(this);

        Bukkit.getPluginManager().registerEvents(new OrganListener(this), this);
        getCommand("organs").setExecutor(new OrgansCommand(this));

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                applyEffects(p);
            }
        }, 0L, 100L);
    }

    private void applyEffects(Player p) {

        int heart = manager.getStrength(p, OrganType.HEART);
        int legs = manager.getStrength(p, OrganType.LEGS);
        int arms = manager.getStrength(p, OrganType.ARMS);
        int eyes = manager.getStrength(p, OrganType.EYES);
        int brain = manager.getStrength(p, OrganType.BRAIN);

        if (legs >= 20)
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, (legs / 20) - 1));

        if (arms >= 20)
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 0));

        if (eyes >= 10)
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 0));

        if (heart >= 30)
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0));

        if (brain >= 20)
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 120, 0));
    }

    public OrganManager getManager() {
        return manager;
    }
}
