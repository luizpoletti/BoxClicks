package com.boxstore.clicks.setup.shop;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.ShopDAO;
import com.boxstore.clicks.data.shop.ShopCategory;
import com.boxstore.clicks.data.shop.ShopItem;
import com.boxstore.clicks.utils.ItemBuilder;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ShopSetup {

    private final FileConfiguration config;

    public ShopSetup(Main main) {
        config = main.getShopFile().getConfig();

        setup();
    }

    public void setup() {
        for (val path : config.getConfigurationSection("Categories").getKeys(false)) {
            val key = config.getConfigurationSection("Categories." + path);

            val title = key.getString("Inventory.Title").replace("&", "§");
            val allowedSlots = key.getIntegerList("Inventory.Allowed-Slots");
            val size = key.getInt("Inventory.Size") * 9;

            val name = key.getString("Icon.Name");
            val skullURL = key.getString("Icon.Skull-URL");
            val permission = key.getString("Permission");
            val lore = key.getStringList("Icon.Lore");

            val material = Material.valueOf(key.getString("Icon.Material").toUpperCase().split(":")[0]);
            val data = Integer.parseInt(key.getString("Icon.Material").split(":")[1]);
            val glow = Boolean.parseBoolean(key.getString("Icon.Material").split(":")[2]);

            val item = new ItemBuilder(material, 1, data).setName(name).setLore(lore).setGlow(glow).build();
            val skull = new ItemBuilder(skullURL).setName(name).setLore(lore).build();

            val icon = key.getBoolean("Icon.Custom-Skull") ? skull : item;

            val category = new ShopCategory(icon, title, permission, new ArrayList<>(), allowedSlots, size);
            for (val itemPath : key.getConfigurationSection("Items").getKeys(false)) {
                val itemKey = key.getConfigurationSection("Items." + itemPath);

                val itemName = itemKey.getString("Icon.Name");
                val itemURL = itemKey.getString("Icon.Skull-URL");
                val itemLore = itemKey.getStringList("Icon.Lore");
                val itemCommands = itemKey.getStringList("Commands");

                val itemPrice = itemKey.getDouble("Price");

                val itemAmount = itemKey.getInt("Icon.Amount");
                val itemMaterial = Material.valueOf(itemKey.getString("Icon.Material").toUpperCase().split(":")[0]);
                val itemData = Integer.parseInt(itemKey.getString("Icon.Material").split(":")[1]);
                val itemGlow = Boolean.parseBoolean(itemKey.getString("Icon.Material").split(":")[2]);

                val itemDefault = new ItemBuilder(itemMaterial, itemAmount, itemData).setName(itemName).setLore(itemLore).setGlow(itemGlow).build();
                val itemSkull = new ItemBuilder(itemURL).setAmount(itemAmount).setName(itemName).setLore(itemLore).build();

                val itemIcon = itemKey.getBoolean("Icon.Custom-Skull") ? itemSkull : itemDefault;

                val shopItem = new ShopItem(itemIcon, itemCommands, itemPrice);
                category.getShopItems().add(shopItem);
            }
            ShopDAO.getShopCategories().add(category);
        }

    }

}
