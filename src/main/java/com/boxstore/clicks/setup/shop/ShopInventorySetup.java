package com.boxstore.clicks.setup.shop;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.shop.CategoryInventory;
import lombok.Getter;
import lombok.val;
import org.bukkit.configuration.file.FileConfiguration;

public class ShopInventorySetup {

    @Getter
    private CategoryInventory categoryInventory;

    private final FileConfiguration config;

    public ShopInventorySetup(Main main) {
        config = main.getShopFile().getConfig();

        setup();
    }

    public void setup() {
        val title = config.getString("Inventory.Title").replace("&", "§");
        val allowedSlots = config.getIntegerList("Inventory.Allowed-Slots");
        val size = config.getInt("Inventory.Size") * 9;

        categoryInventory = new CategoryInventory(title, allowedSlots, size);
    }

}
