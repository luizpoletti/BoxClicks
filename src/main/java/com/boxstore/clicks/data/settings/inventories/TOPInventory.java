package com.boxstore.clicks.data.settings.inventories;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TOPInventory {

    private String title;
    private String name;
    private List<String> lore;
    private List<Integer> allowedSlots;
    private int size;

}
