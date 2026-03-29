package com.infamous.organsmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class OrgansCommand implements CommandExecutor {

    private final OrganSMP plugin;

    public OrgansCommand(OrganSMP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_RED + "Your Organs");

        OrganManager mgr = plugin.getManager();

        int slot = 2;

        for (OrganType type : OrganType.values()) {

            int str = mgr.getStrength(p, type);

            ItemStack item = new ItemStack(type.icon);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(ChatColor.RED + type.name);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Strength: " + str + "/50");
            lore.add(ChatColor.YELLOW + "Level: " + (str / 10));

            meta.setLore(lore);
            item.setItemMeta(meta);

            inv.setItem(slot++, item);
        }

        p.openInventory(inv);
        return true;
    }
}
