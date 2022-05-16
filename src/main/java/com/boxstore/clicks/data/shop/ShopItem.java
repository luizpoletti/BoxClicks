package com.boxstore.clicks.data.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
@Data
public class ShopItem {

    private ItemStack icon;
    private List<String> commands;
    private double price;

}
