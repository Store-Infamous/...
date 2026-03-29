package com.infamous.organsmp;

import org.bukkit.Material;

public enum OrganType {
    HEART("Heart", Material.NETHER_WART),
    EYES("Eyes", Material.ENDER_EYE),
    LEGS("Legs", Material.DIAMOND_BOOTS),
    ARMS("Arms", Material.NETHERITE_SWORD),
    BRAIN("Brain", Material.CHORUS_FRUIT);

    public final String name;
    public final Material icon;

    OrganType(String name, Material icon) {
        this.name = name;
        this.icon = icon;
    }
}
