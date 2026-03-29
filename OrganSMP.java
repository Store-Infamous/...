package com.infamous.organsmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList(this), this);
        getCommand("organs").setExecutor(this);

        // Core Loop: Apply buffs every 5 seconds
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                applyOrganEffects(player);
            }
        }, 0L, 100L);
    }

    public void applyOrganEffects(Player p) {
        int legs = getStrength(p, "LEGS");
        int arms = getStrength(p, "ARMS");
        int eyes = getStrength(p, "EYES");
        int heart = getStrength(p, "HEART");

        if (legs >= 20) p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 150, (legs / 20) - 1));
        if (arms >= 20) p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 150, 0));
        if (eyes >= 10) p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 0));
        if (heart >= 30) p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 150, 0));
    }

    public int getStrength(Player p, String organ) {
        NamespacedKey key = new NamespacedKey(this, organ.toLowerCase());
        return p.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

    public void setStrength(Player p, String organ, int amount) {
        NamespacedKey key = new NamespacedKey(this, organ.toLowerCase());
        p.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Math.min(amount, 50));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command ChatColor.DARK_RED + "Your Organs");
        String[] types = {"HEART", "EYES", "LEGS", "ARMS", "BRAIN"};
        Material[] icons = {Material.NETHER_WART, Material.ENDER_EYE, Material.DIAMOND_BOOTS, Material.NETHERITE_SWORD, Material.CHORUS_FRUIT};

        for (int i = 0; i < types.length; i++) {
            ItemStack item = new ItemStack(icons[i]);
            ItemMeta meta = item.getItemMeta();
            int str = getStrength(p, types[i]);
            meta.setDisplayName(ChatColor.RED + types[i]);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Strength: " + ChatColor.WHITE + str + "/50");
            lore.add(ChatColor.GRAY + "Level: " + ChatColor.YELLOW + (str / 10));
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(i + 2, item);
        }
        p.openInventory(inv);
    }

    public Random getRandom() { return random; }
}
