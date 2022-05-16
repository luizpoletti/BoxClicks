package com.boxstore.clicks.inventories;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.data.settings.Sounds;
import com.boxstore.clicks.data.settings.inventories.ClickInventory;
import com.boxstore.clicks.utils.Format;
import com.boxstore.clicks.utils.ItemBuilder;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ClicksInventory {

    protected Main main;

    private final ClickInventory clickInventory;
    private final Sounds sounds;

    public ClicksInventory(Main main) {
        this.main = main;

        clickInventory = main.getClickSetup().getClickInventory();
        sounds = main.getSoundsSetup().getSounds();
    }

    public void open(Player player) {
        val inventory = Bukkit.createInventory(null, clickInventory.getSize(), clickInventory.getTitle());
        val user = UserDAO.getUser(player.getUniqueId());

        val click = clickInventory.getClick();
        val top = clickInventory.getTop();
        val shop = clickInventory.getShop();

        List<String> lore = click.getLore();
        lore = lore.stream().map(l -> l.replace("{clicks}", Format.formatNumber(user.getClicks()))).collect(Collectors.toList());

        val clickItem = new ItemBuilder(click.getMaterial(), 1, click.getData()).setName(click.getName()).setLore(lore).setGlow(click.isGlow()).build();
        val clickSkull = new ItemBuilder(click.getUrl()).setName(click.getName()).setLore(lore).build();

        inventory.setItem(click.getSlot(), click.isCustomSkull() ? clickSkull : clickItem);
        inventory.setItem(top.getSlot(), top.getIcon());
        inventory.setItem(shop.getSlot(), shop.getIcon());

        player.openInventory(inventory);
        TangramUtils.playSound(player, sounds.getOpenInventory());
    }

}
