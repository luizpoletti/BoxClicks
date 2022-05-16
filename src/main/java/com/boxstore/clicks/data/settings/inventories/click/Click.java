package com.boxstore.clicks.data.settings.inventories.click;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
@Getter
public class Click {

    private String name;
    private String url;
    private List<String> lore;
    private Material material;
    private int data;
    private int slot;
    private boolean customSkull;
    private boolean glow;

}
