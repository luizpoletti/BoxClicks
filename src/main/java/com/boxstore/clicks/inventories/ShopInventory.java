package com.boxstore.clicks.inventories;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.Sounds;
import com.boxstore.clicks.data.shop.ShopCategory;
import com.boxstore.clicks.utils.Format;
import com.boxstore.clicks.utils.ItemBuilder;
import com.boxstore.clicks.utils.Scroller.ScrollerBuilder;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopInventory {

    private final Sounds sounds;

    public ShopInventory(Main main) {
        sounds = main.getSoundsSetup().getSounds();
    }

    public void open(Player player, ShopCategory category) {
        val items = new ArrayList<ItemStack>();

        category.getShopItems().forEach(shopItems -> {
            List<String> lore = shopItems.getIcon().getItemMeta().getLore();
            lore = lore.stream().map(l -> l.replace("{price}", Format.formatNumber(shopItems.getPrice()))).collect(Collectors.toList());

            val icon = new ItemBuilder(shopItems.getIcon()).setLore(lore).build();

            items.add(icon);
        });
        val scroller = new ScrollerBuilder().withName(category.getTitle()).withSize(category.getSize()).withAllowedSlots(category.getAllowedSlots()).withItems(items).build();
        scroller.open(player);

        TangramUtils.playSound(player, sounds.getOpenInventory());
    }

}
