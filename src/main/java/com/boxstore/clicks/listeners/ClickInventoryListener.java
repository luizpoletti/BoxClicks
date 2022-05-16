package com.boxstore.clicks.listeners;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.data.settings.inventories.ClickInventory;
import com.boxstore.clicks.inventories.loader.Inventories;
import com.boxstore.clicks.registry.EventRegistry;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickInventoryListener extends EventRegistry {

    private final Inventories inventories;
    private final ClickInventory clickInventory;

    public ClickInventoryListener(Main main) {
        super(main);

        inventories = main.getInventories();
        clickInventory = main.getClickSetup().getClickInventory();
    }

    @EventHandler
    void clickInventory(InventoryClickEvent event) {
        val title = event.getView().getTitle();
        if (!title.equals(clickInventory.getTitle()))
            return;

        val item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR)
            return;

        event.setCancelled(true);
        val player = (Player) event.getWhoClicked();
        val user = UserDAO.getUser(player.getUniqueId());
        val slot = event.getSlot();

        val click = clickInventory.getClick();
        val top = clickInventory.getTop();
        val shop = clickInventory.getShop();

        if (slot == click.getSlot()) {
            user.addClicks(1);
            inventories.getClicksInventory().open(player);
            return;

        }
        if (slot == top.getSlot())
            inventories.getClickTOPInventory().open(player);

        else if (slot == shop.getSlot())
            inventories.getClickShopInventory().open(player);
    }

}
