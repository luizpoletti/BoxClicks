package com.boxstore.clicks.commands;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.inventories.ClicksInventory;
import com.boxstore.clicks.inventories.loader.Inventories;
import lombok.val;
import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class ClicksCommand {

    protected Main main;

    private final Inventories inventories;

    public ClicksCommand(Main main) {
        this.main = main;

        inventories = main.getInventories();
    }

    @Command(name = "clicks", aliases = {"click", "cliques", "clique"}, target = CommandTarget.PLAYER, async = true)
    public void clicksCommand(BukkitContext context) {
        val player = (Player) context.getSender();
        inventories.getClicksInventory().open(player);
    }

}
