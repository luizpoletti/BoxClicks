package com.boxstore.clicks.data.settings.inventories;

import com.boxstore.clicks.data.settings.inventories.click.Click;
import com.boxstore.clicks.data.settings.inventories.click.Shop;
import com.boxstore.clicks.data.settings.inventories.click.Top;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClickInventory {

    private String title;
    private Click click;
    private Top top;
    private Shop shop;
    private int size;

}
