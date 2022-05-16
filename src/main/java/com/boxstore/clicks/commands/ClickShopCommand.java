package com.boxstore.clicks.commands;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.inventories.loader.Inventories;
import lombok.val;
import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class ClickShopCommand {

    protected Main main;

    private final Inventories inventories;

    public ClickShopCommand(Main main) {
        this.main = main;

        inventories = main.getInventories();
    }

    @Command(name = "clicks.shop", aliases = {"loja"}, target = CommandTarget.PLAYER, async = true)
    public void clickShopCommand(BukkitContext context) {
        val player = (Player) context.getSender();
        inventories.getClickShopInventory().open(player);
    }

}
