package com.boxstore.clicks.setup;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.Messages;
import lombok.Getter;
import lombok.val;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class MessagesSetup {

    @Getter
    private Messages messages;

    private final FileConfiguration config;

    public MessagesSetup(Main main) {
        config = main.getMessagesFile().getConfig();

        setup();
    }

    public void setup() {
        List<String> playerCommandList = config.getStringList("Command-List.Player");
        playerCommandList = playerCommandList.stream().map(cl -> cl.replace("&", "§")).collect(Collectors.toList());
        List<String> adminCommandList = config.getStringList("Command-List.Admin");
        adminCommandList = adminCommandList.stream().map(cl -> cl.replace("&", "§")).collect(Collectors.toList());

        val incorrectUsage = config.getString("Incorrect-Usage").replace("&", "§");

        val seeClicks = config.getStringList("Clicks.See");
        val addedClicks = config.getString("Clicks.Added");
        val settedClicks = config.getString("Clicks.Setted");
        val removedClicks = config.getString("Clicks.Removed");

        val noClicks = config.getString("Shop.No-Clicks");
        val successfulPurchase = config.getStringList("Shop.Successful-Purchase");

        messages = new Messages(incorrectUsage, addedClicks, settedClicks, removedClicks, noClicks, playerCommandList, adminCommandList, seeClicks, successfulPurchase);
    }

}
