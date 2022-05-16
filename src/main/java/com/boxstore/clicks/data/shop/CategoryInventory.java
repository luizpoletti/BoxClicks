package com.boxstore.clicks.data.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CategoryInventory {

    private String title;
    private List<Integer> allowedSlots;
    private int size;

}
