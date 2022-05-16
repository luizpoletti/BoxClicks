package com.boxstore.clicks.inventories.loader;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.inventories.ClickShopInventory;
import com.boxstore.clicks.inventories.ClickTOPInventory;
import com.boxstore.clicks.inventories.ClicksInventory;
import com.boxstore.clicks.inventories.ShopInventory;
import lombok.Data;

@Data(staticConstructor = "of")
public class Inventories {

    protected final Main main;

    private ClicksInventory clicksInventory;
    private ClickTOPInventory clickTOPInventory;
    private ClickShopInventory clickShopInventory;
    private ShopInventory shopInventory;

    public void load() {
        clicksInventory = new ClicksInventory(main);
        clickTOPInventory = new ClickTOPInventory(main);
        clickShopInventory = new ClickShopInventory(main);
        shopInventory = new ShopInventory(main);
    }

}
