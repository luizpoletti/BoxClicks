package com.boxstore.clicks.setup.inventories;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.inventories.ClickInventory;
import com.boxstore.clicks.data.settings.inventories.click.Click;
import com.boxstore.clicks.data.settings.inventories.click.Shop;
import com.boxstore.clicks.data.settings.inventories.click.Top;
import com.boxstore.clicks.utils.ItemBuilder;
import lombok.Getter;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class ClickSetup {

    @Getter
    private ClickInventory clickInventory;

    private final FileConfiguration config;

    public ClickSetup(Main main) {
        config = main.getClickFile().getConfig();

        setup();
    }

    public void setup() {
        val title = config.getString("Inventory.Title").replace("&", "§");
        val size = config.getInt("Inventory.Size") * 9;

        val clickName = config.getString("Click-Icon.Name");
        val clickSkull = config.getString("Click-Icon.Skull-URL");
        val clickLore = config.getStringList("Click-Icon.Lore");
        val clickSlot = config.getInt("Click-Icon.Slot");
        val clickCustomSkull = config.getBoolean("Click-Icon.Custom-Skull");
        val clickMaterial = Material.valueOf(config.getString("Click-Icon.Material").toUpperCase().split(":")[0]);
        val clickData = Integer.parseInt(config.getString("Click-Icon.Material").split(":")[1]);
        val clickGlow = Boolean.parseBoolean(config.getString("Click-Icon.Material").split(":")[2]);

        val topName = config.getString("TOP-Icon.Name");
        val topSkull = config.getString("TOP-Icon.Skull-URL");
        val topLore = config.getStringList("TOP-Icon.Lore");
        val topSlot = config.getInt("TOP-Icon.Slot");
        val topCustomSkull = config.getBoolean("TOP-Icon.Custom-Skull");
        val topMaterial = Material.valueOf(config.getString("TOP-Icon.Material").toUpperCase().split(":")[0]);
        val topData = Integer.parseInt(config.getString("TOP-Icon.Material").split(":")[1]);
        val topGlow = Boolean.parseBoolean(config.getString("TOP-Icon.Material").split(":")[2]);

        val topItem = new ItemBuilder(topMaterial, 1, topData).setName(topName).setLore(topLore).setGlow(topGlow).build();
        val topHead = new ItemBuilder(topSkull).setName(topName).setLore(topLore).build();
        val topIcon = topCustomSkull ? topHead : topItem;

        val shopName = config.getString("Shop-Icon.Name");
        val shopSkull = config.getString("Shop-Icon.Skull-URL");
        val shopLore = config.getStringList("Shop-Icon.Lore");
        val shopSlot = config.getInt("Shop-Icon.Slot");
        val shopCustomSkull = config.getBoolean("Shop-Icon.Custom-Skull");
        val shopMaterial = Material.valueOf(config.getString("Shop-Icon.Material").toUpperCase().split(":")[0]);
        val shopData = Integer.parseInt(config.getString("Shop-Icon.Material").split(":")[1]);
        val shopGlow = Boolean.parseBoolean(config.getString("Shop-Icon.Material").split(":")[2]);

        val shopItem = new ItemBuilder(shopMaterial, 1, shopData).setName(shopName).setLore(shopLore).setGlow(shopGlow).build();
        val shopHead = new ItemBuilder(shopSkull).setName(shopName).setLore(shopLore).build();
        val shopIcon = shopCustomSkull ? shopHead : shopItem;

        val click = new Click(clickName, clickSkull, clickLore, clickMaterial, clickData, clickSlot, clickCustomSkull, clickGlow);
        val top = new Top(topIcon, topSlot);
        val shop = new Shop(shopIcon, shopSlot);

        clickInventory = new ClickInventory(title, click, top, shop, size);
    }

}
