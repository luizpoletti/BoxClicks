package com.boxstore.clicks.inventories;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.data.settings.Sounds;
import com.boxstore.clicks.data.settings.inventories.TOPInventory;
import com.boxstore.clicks.data.user.User;
import com.boxstore.clicks.utils.Format;
import com.boxstore.clicks.utils.ItemBuilder;
import com.boxstore.clicks.utils.Scroller.ScrollerBuilder;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ClickTOPInventory {

    private final TOPInventory topInventory;
    private final Sounds sounds;

    public ClickTOPInventory(Main main) {
        topInventory = main.getTopSetup().getTopInventory();
        sounds = main.getSoundsSetup().getSounds();
    }

    public void open(Player player) {
        val items = new ArrayList<ItemStack>();

        List<String> lore = topInventory.getLore();
        val position = new AtomicInteger(1);
        val topUsers = UserDAO.getUsers().stream().sorted(Comparator.comparingDouble(User::getClicks).reversed()).limit(10).collect(Collectors.toList());

        for (val users : topUsers) {
            val players = users.getPlayer();
            lore = lore.stream().map(l -> l.replace("{player}", players.getName()).replace("{clicks}", Format.formatNumber(users.getClicks())).replace("{pos}", "" + position.get())).collect(Collectors.toList());

            val icon = new ItemBuilder(TangramUtils.getPlayerHead(players.getName())).setName(topInventory.getName().replace("{pos}", "" + position.get())).setLore(lore).build();

            items.add(icon);
            position.getAndIncrement();
        }
        val scroller = new ScrollerBuilder().withName(topInventory.getTitle()).withSize(topInventory.getSize()).withAllowedSlots(topInventory.getAllowedSlots()).withItems(items).build();
        scroller.open(player);

        TangramUtils.playSound(player, sounds.getOpenInventory());
    }

}
