package com.boxstore.clicks.listeners;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.api.ClicksAPI;
import com.boxstore.clicks.registry.EventRegistry;
import lombok.val;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinQuitListener extends EventRegistry {

    private final ClicksAPI clicksAPI;

    public JoinQuitListener(Main main) {
        super(main);

        clicksAPI = main.getClicksAPI();
    }

    @EventHandler
    void playerJoin(PlayerJoinEvent event) {
        val player = event.getPlayer();
        if (!clicksAPI.hasAccount(player.getUniqueId()))
            clicksAPI.createAccount(player.getUniqueId());
    }

}
