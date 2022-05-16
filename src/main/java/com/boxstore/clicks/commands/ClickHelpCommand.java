package com.boxstore.clicks.commands;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.Messages;
import com.boxstore.clicks.data.settings.Sounds;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.target.CommandTarget;

public class ClickHelpCommand {

    private final Messages messages;
    private final Sounds sounds;

    public ClickHelpCommand(Main main) {
        messages = main.getMessagesSetup().getMessages();
        sounds = main.getSoundsSetup().getSounds();
    }

    @Command(name = "clicks.help", aliases = {"ajuda", "comandos", "commands"}, target = CommandTarget.PLAYER, async = true)
    public void clicksTOPCommand(BukkitContext context) {
        val sender = context.getSender();
        if (sender.hasPermission("box.clicks.admin"))
            messages.getAdminCommandList().forEach(sender::sendMessage);

        else
            messages.getPlayerCommandList().forEach(sender::sendMessage);

        TangramUtils.playSound(sender, sounds.getCommandList());
    }

}
