package com.boxstore.clicks;

import com.boxstore.clicks.api.ClicksAPI;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.files.*;
import com.boxstore.clicks.inventories.loader.Inventories;
import com.boxstore.clicks.listeners.*;
import com.boxstore.clicks.placeholders.ClicksHolder;
import com.boxstore.clicks.registry.CommandRegistry;
import com.boxstore.clicks.setup.MessagesSetup;
import com.boxstore.clicks.setup.SoundsSetup;
import com.boxstore.clicks.setup.inventories.ClickSetup;
import com.boxstore.clicks.setup.inventories.TOPSetup;
import com.boxstore.clicks.setup.shop.ShopInventorySetup;
import com.boxstore.clicks.setup.shop.ShopSetup;
import com.boxstore.clicks.storage.DBProvider;
import com.boxstore.clicks.storage.providers.MySQL;
import com.boxstore.clicks.storage.providers.SQLite;
import com.boxstore.clicks.utils.MultipleFile;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    private DBProvider dbProvider;

    private ClicksAPI clicksAPI;

    private ShopFile shopFile;
    private ClickFile clickFile;
    private ClickTOPFile clickTOPFile;
    private MessagesFile messagesFile;
    private SoundsFile soundsFile;

    private ShopInventorySetup shopInventorySetup;
    private ClickSetup clickSetup;
    private TOPSetup topSetup;
    private MessagesSetup messagesSetup;
    private SoundsSetup soundsSetup;

    private Inventories inventories;

    @Override
    public void onEnable() {
        loadFiles();
        TangramUtils.sendMessage(Bukkit.getConsoleSender(), "§fDeveloper: §7LuizBebe840#9887");
        TangramUtils.sendMessage(Bukkit.getConsoleSender(), "§fDiscord Server: §7https://discord.gg/3Cu54WNzJq");
        loadSQL();
        registerObjects();
        register();
    }

    @Override
    public void onDisable() {
        UserDAO.getUsers().forEach(clicksAPI::save);
        dbProvider.closeConnection();
    }

    private void register() {
        new CommandRegistry(this).registerCommands();
        new JoinQuitListener(this);
        new ClickInventoryListener(this);
        new CategorySelectListener(this);
        new BuyItemListener(this);

        if (getConfig().getBoolean("Settings.Count-Hits"))
            new DamageListener(this);
    }

    private void registerObjects() {
        shopInventorySetup = new ShopInventorySetup(this);
        clickSetup = new ClickSetup(this);
        topSetup = new TOPSetup(this);
        messagesSetup = new MessagesSetup(this);
        soundsSetup = new SoundsSetup(this);

        inventories = Inventories.of(this);
        inventories.load();

        new ShopSetup(this);
        new ClicksHolder(this);
    }

    private void loadSQL() {
        dbProvider = getConfig().getBoolean("MySQL.Enable") ? new MySQL(this) : new SQLite();

        clicksAPI = new ClicksAPI(this);
        UserDAO.getUsers().addAll(clicksAPI.findAll());
    }

    private void loadFiles() {
        saveDefaultConfig();

        new MultipleFile(this, null, "help.yml");
        new MultipleFile(this, "cache");

        shopFile = new ShopFile(this);
        clickFile = new ClickFile(this);
        clickTOPFile = new ClickTOPFile(this);
        messagesFile = new MessagesFile(this);
        soundsFile = new SoundsFile(this);
    }

}
