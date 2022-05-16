package com.boxstore.clicks.listeners;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.ShopDAO;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.registry.EventRegistry;
import com.boxstore.clicks.utils.Format;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyItemListener extends EventRegistry {

    public BuyItemListener(Main main) {
        super(main);
    }

    @EventHandler
    void buyItem(InventoryClickEvent event) {
        val title = event.getView().getTitle();
        val category = ShopDAO.getCategory(title);
        if (category == null)
            return;

        val item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR)
            return;

        val player = (Player) event.getWhoClicked();
        val user = UserDAO.getUser(player.getUniqueId());
        val shopItem = category.getShopItem(item);
        if (shopItem == null)
            return;

        val itemName = shopItem.getIcon().getItemMeta().getDisplayName();
        if (!user.hasClicks(shopItem.getPrice())) {
            TangramUtils.sendMessage(player, messages.getNoClicks().replace("{item-name}", itemName));
            TangramUtils.playSound(player, sounds.getNoClicks());
            return;

        }
        TangramUtils.runCommandList(player, shopItem.getCommands());
        TangramUtils.playSound(player, sounds.getSuccessfulPurchase());
        messages.getSuccessfulPurchase().forEach(message -> TangramUtils.sendMessage(player, message.replace("{item-name}", itemName).replace("{price}", Format.formatNumber(shopItem.getPrice()))));

        user.removeClicks(shopItem.getPrice());
    }

}
