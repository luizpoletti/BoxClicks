package com.boxstore.clicks.commands;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.data.settings.Messages;
import com.boxstore.clicks.data.settings.Sounds;
import com.boxstore.clicks.utils.Format;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import me.saiintbrisson.bukkit.command.command.BukkitContext;
import me.saiintbrisson.minecraft.command.annotation.Command;
import org.bukkit.Bukkit;

public class AddClickCommand {

    private final Messages messages;
    private final Sounds sounds;

    public AddClickCommand(Main main) {
        messages = main.getMessagesSetup().getMessages();
        sounds = main.getSoundsSetup().getSounds();
    }

    @Command(name = "clicks.add", aliases = {"adicionar", "give"}, usage = "clicks add <player> <amount>", async = true)
    public void addClickCommand(BukkitContext context) {
        val sender = context.getSender();
        if (!TangramUtils.hasPermission(sender, "box.clicks.admin"))
            return;

        val args = context.getArgs();
        val target = Bukkit.getPlayer(args[0]);
        if (!TangramUtils.playerIsOnline(sender, target))
            return;

        val amount = Format.parseDouble(args[1]);
        if (!TangramUtils.isNumber(sender, String.valueOf(amount)))
            return;

        if (TangramUtils.isZero(sender, amount))
            return;

        val user = UserDAO.getUser(target.getUniqueId());

        TangramUtils.sendMessage(sender, messages.getAddedClicks().replace("{player}", target.getName()).replace("{amount}", Format.formatNumber(amount)));
        TangramUtils.playSound(sender, sounds.getAddedClicks());
        user.addClicks(amount);
    }

}
