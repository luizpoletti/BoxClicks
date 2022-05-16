package com.boxstore.clicks.data.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
@Data
public class ShopCategory {

    private ItemStack icon;
    private String title;
    private String permission;
    private List<ShopItem> shopItems;
    private List<Integer> allowedSlots;
    private int size;

    public ShopItem getShopItem(ItemStack icon) {
        return shopItems.stream().filter(shopItem -> shopItem.getIcon().isSimilar(icon)).findFirst().orElse(null);
    }

}
