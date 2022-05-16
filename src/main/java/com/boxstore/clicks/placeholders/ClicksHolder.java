package com.boxstore.clicks.placeholders;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.utils.Format;
import lombok.val;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClicksHolder extends PlaceholderExpansion {

    protected Main main;

    public ClicksHolder(Main main) {
        this.main = main;
        super.register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "boxclicks";
    }

    @Override
    public @NotNull String getAuthor() {
        return "LuizBebe840";
    }

    @Override
    public @NotNull String getVersion() {
        return main.getDescription().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        val user = UserDAO.getUser(player.getUniqueId());
        if (params.equalsIgnoreCase("raw_amount"))
            return String.valueOf(user.getClicks());

        if (params.equalsIgnoreCase("amount"))
            return Format.formatNumber(user.getClicks());

        return "-/-";
    }

}
