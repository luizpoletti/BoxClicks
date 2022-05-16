package com.boxstore.clicks.registry;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.commands.*;
import com.boxstore.clicks.data.settings.Messages;
import lombok.val;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageType;

public class CommandRegistry {

    protected Main main;

    private final Messages messages;

    public CommandRegistry(Main main) {
        this.main = main;

        messages = main.getMessagesSetup().getMessages();
    }

    public void registerCommands() {
        try {
            val bukkit = new BukkitFrame(main);
            val message = bukkit.getMessageHolder();

            message.setMessage(MessageType.INCORRECT_USAGE, messages.getIncorrectUsage().replace("<usage>", "{usage}"));
            bukkit.registerCommands(
                    new ClicksCommand(main),
                    new ClickHelpCommand(main),
                    new ClickTOPCommand(main),
                    new ClickShopCommand(main),
                    new SeeClicksCommand(main),
                    new AddClickCommand(main),
                    new SetClickCommand(main),
                    new RemoveClickCommand(main)
            );
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

}
