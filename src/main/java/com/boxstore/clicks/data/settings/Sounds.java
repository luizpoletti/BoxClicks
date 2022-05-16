package com.boxstore.clicks.data.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Sound;

@AllArgsConstructor
@Getter
public class Sounds {

    private Sound openInventory;
    private Sound commandList;
    private Sound incorrectUsage;
    private Sound addedClicks;
    private Sound settedClicks;
    private Sound removedClicks;
    private Sound seeClicks;
    private Sound noClicks;
    private Sound successfulPurchase;

}
