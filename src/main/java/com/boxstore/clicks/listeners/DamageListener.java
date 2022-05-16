package com.boxstore.clicks.listeners;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.registry.EventRegistry;
import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener extends EventRegistry {

    public DamageListener(Main main) {
        super(main);
    }

    @EventHandler
    void playerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        val player = (Player) event.getDamager();
        val user = UserDAO.getUser(player.getUniqueId());

        user.addClicks(1);
    }

}
