package com.boxstore.clicks.dao;

import com.boxstore.clicks.data.shop.ShopCategory;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    @Getter
    private static final List<ShopCategory> shopCategories = new ArrayList<>();

    public static ShopCategory getCategory(ItemStack icon) {
        return shopCategories.stream().filter(shop -> shop.getIcon().isSimilar(icon)).findFirst().orElse(null);
    }

    public static ShopCategory getCategory(String title) {
        return shopCategories.stream().filter(shop -> shop.getTitle().equals(title)).findFirst().orElse(null);
    }

}
