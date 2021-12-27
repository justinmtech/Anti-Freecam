package com.justinmtech.antifreecam;

import com.justinmtech.antifreecam.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiFreecam extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
    }
}
