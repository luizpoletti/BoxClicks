package com.boxstore.clicks.listeners;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.ShopDAO;
import com.boxstore.clicks.data.shop.CategoryInventory;
import com.boxstore.clicks.inventories.loader.Inventories;
import com.boxstore.clicks.registry.EventRegistry;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CategorySelectListener extends EventRegistry {

    private final Inventories inventories;
    private final CategoryInventory categoryInventory;

    public CategorySelectListener(Main main) {
        super(main);

        inventories = main.getInventories();
        categoryInventory = main.getShopInventorySetup().getCategoryInventory();
    }

    @EventHandler
    void categorySelect(InventoryClickEvent event) {
        val title = event.getView().getTitle();
        if (!title.equals(categoryInventory.getTitle()))
            return;

        val item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR)
            return;

        val player = (Player) event.getWhoClicked();
        val category = ShopDAO.getCategory(item);
        if (category == null)
            return;

        inventories.getShopInventory().open(player, category);
    }

}
