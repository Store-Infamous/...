package com.infamous.organsmp;

importManager(OrganSMP plugin) {
        this.plugin = plugin;
    }

    public int getStrength(Player p, OrganType type) {
        NamespacedKey key = new NamespacedKey(plugin, type.name());
        return p.getPersistentDataContainer().getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

    public void setStrength(Player p, OrganType type, int val) {
        NamespacedKey key = new NamespacedKey(plugin, type.name());
        p.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Math.min(val, 50));
    }

    public OrganType getStrongest(Player p) {
        OrganType strongest = null;
        int max = 0;
        for (OrganType type : OrganType.values()) {
            int str = getStrength(p, type);
            if (str > max) {
                max = str;
                strongest = type;
            }
        }
        return strongest;
    }

    public OrganType getRandom() {
        return OrganType.values()[random.nextInt(OrganType.values().length)];
    }
}
