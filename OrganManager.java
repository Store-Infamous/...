package com.infamous.organsmp;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class OrganManager {

    private final OrganSMP plugin;
    private final Random random = new Random();

    public OrganManager(OrganSMP plugin) {
        this.plugin = plugin;
    }

    public int getStrength(Player p, OrganType type) {
        NamespacedKey key = new NamespacedKey(plugin, type.name());
        return p.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

    public void setStrength(Player p, OrganType type, int value) {
        NamespacedKey key = new NamespacedKey(plugin, type.name());
        p.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Math.min(value, 50));
    }

    public OrganType getStrongest(Player p) {
        OrganType best = null;
        int max = 0;

        for (OrganType type : OrganType.values()) {
            int val = getStrength(p, type);
            if (val > max) {
                max = val;
                best = type;
            }
        }
        return best;
    }

    public OrganType getRandom() {
        return OrganType.values()[random.nextInt(OrganType.values().length)];
    }
}
