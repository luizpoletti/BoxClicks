package com.boxstore.clicks.setup;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.Sounds;
import lombok.Getter;
import lombok.val;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class SoundsSetup {

    @Getter
    private Sounds sounds;

    private final FileConfiguration config;

    public SoundsSetup(Main main) {
        config = main.getSoundsFile().getConfig();

        setup();
    }

    public void setup() {
        val openInventory = Sound.valueOf(config.getString("Open-Inventories").toUpperCase());
        val commandList = Sound.valueOf(config.getString("Command-List").toUpperCase());
        val incorrectUsage = Sound.valueOf(config.getString("Incorrect-Usage").toUpperCase());

        val addedClicks = Sound.valueOf(config.getString("Clicks.Added").toUpperCase());
        val settedClicks = Sound.valueOf(config.getString("Clicks.Setted").toUpperCase());
        val removedClicks = Sound.valueOf(config.getString("Clicks.Removed").toUpperCase());
        val seeClicks = Sound.valueOf(config.getString("Clicks.See").toUpperCase());

        val noClicks = Sound.valueOf(config.getString("Shop.No-Clicks").toUpperCase());
        val successfulPurchase = Sound.valueOf(config.getString("Shop.Successful-Purchase").toUpperCase());

        sounds = new Sounds(openInventory, commandList, incorrectUsage, addedClicks, settedClicks, removedClicks, seeClicks, noClicks, successfulPurchase);
    }

}
