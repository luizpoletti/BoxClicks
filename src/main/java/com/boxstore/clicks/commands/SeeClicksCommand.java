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

public class SeeClicksCommand {

    private final Messages messages;
    private final Sounds sounds;

    public SeeClicksCommand(Main main) {
        messages = main.getMessagesSetup().getMessages();
        sounds = main.getSoundsSetup().getSounds();
    }

    @Command(name = "clicks.see", aliases = {"ver", "show", "view"}, usage = "clicks see <player>", async = true)
    public void seeClicksCommand(BukkitContext context) {
        val sender = context.getSender();
        val args = context.getArgs();

        val target = Bukkit.getPlayer(args[0]);
        if (!TangramUtils.playerIsOnline(sender, target))
            return;

        val user = UserDAO.getUser(target.getUniqueId());

        TangramUtils.playSound(sender, sounds.getSeeClicks());
        messages.getSeeClicks().forEach(message -> TangramUtils.sendMessage(sender, message.replace("{player}", target.getName()).replace("{clicks}", Format.formatNumber(user.getClicks()))));
    }

}
