package com.boxstore.clicks.setup.inventories;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.inventories.TOPInventory;
import lombok.Getter;
import lombok.val;
import org.bukkit.configuration.file.FileConfiguration;

public class TOPSetup {

    @Getter
    private TOPInventory topInventory;

    private final FileConfiguration config;

    public TOPSetup(Main main) {
        config = main.getClickTOPFile().getConfig();

        setup();
    }

    public void setup() {
        val title = config.getString("Inventory.Title").replace("&", "§");
        val name = config.getString("Icon.Name");

        val lore = config.getStringList("Icon.Lore");
        val allowedSlots = config.getIntegerList("Inventory.Allowed-Slots");

        val size = config.getInt("Inventory.Size") * 9;

        topInventory = new TOPInventory(title, name, lore, allowedSlots, size);
    }

}
