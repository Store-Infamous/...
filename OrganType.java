package com.infamous.organsmp;

import org.bukkit.Material;

public enum OrganType {
    HEART("Heart", Material.NETHER_WART, "Regeneration"),
    EYES("Eyes", Material.ENDER_EYE, "Night Vision"),
    LEGS("Legs", Material.DIAMOND_BOOTS, "Speed"),
    ARMS("Arms", Material.NETHERITE_SWORD, "Strength"),
    BRAIN("Brain", Material.CHORUS_FRUIT, "Haste/IQ");

    public final String name;
    public final Material icon;
    public final String buff;

    OrganType(String name, Material icon, String buff) {
        this.name = name;
        this.icon = icon;
        this.buff = buff;
    }
}
