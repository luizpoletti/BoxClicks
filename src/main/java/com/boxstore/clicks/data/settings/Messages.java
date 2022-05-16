package com.boxstore.clicks.data.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Messages {

    private String incorrectUsage;
    private String addedClicks;
    private String settedClicks;
    private String removedClicks;
    private String noClicks;
    private List<String> playerCommandList;
    private List<String> adminCommandList;
    private List<String> seeClicks;
    private List<String> successfulPurchase;

}
