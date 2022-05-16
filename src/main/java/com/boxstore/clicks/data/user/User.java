package com.boxstore.clicks.data.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@AllArgsConstructor
@Data
public class User {

    private UUID uuid;
    private double clicks;

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public boolean hasClicks(double amount) {
        return clicks >= amount;
    }

    public synchronized void addClicks(double amount) {
        setClicks(clicks + amount);
    }

    public synchronized void removeClicks(double amount) {
        setClicks(clicks - amount);
    }

}
