package com.boxstore.clicks.registry;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.data.settings.Messages;
import com.boxstore.clicks.data.settings.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class EventRegistry implements Listener {

    protected Main main;

    protected Messages messages;
    protected Sounds sounds;

    public EventRegistry(Main main) {
        this.main = main;

        messages = main.getMessagesSetup().getMessages();
        sounds = main.getSoundsSetup().getSounds();

        Bukkit.getPluginManager().registerEvents(this, main);
    }

}
