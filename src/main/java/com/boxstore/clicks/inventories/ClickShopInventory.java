package com.boxstore.clicks.inventories;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.ShopDAO;
import com.boxstore.clicks.data.settings.Sounds;
import com.boxstore.clicks.data.shop.CategoryInventory;
import com.boxstore.clicks.utils.Scroller.ScrollerBuilder;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ClickShopInventory {

    private final CategoryInventory categoryInventory;
    private final Sounds sounds;

    public ClickShopInventory(Main main) {
        categoryInventory = main.getShopInventorySetup().getCategoryInventory();
        sounds = main.getSoundsSetup().getSounds();
    }

    public void open(Player player) {
        val items = new ArrayList<ItemStack>();

        ShopDAO.getShopCategories().stream().filter(categories -> player.hasPermission(categories.getPermission())).forEach(categories -> items.add(categories.getIcon()));

        val scroller = new ScrollerBuilder().withName(categoryInventory.getTitle()).withSize(categoryInventory.getSize()).withAllowedSlots(categoryInventory.getAllowedSlots()).withItems(items).build();
        scroller.open(player);

        TangramUtils.playSound(player, sounds.getOpenInventory());
    }

}
