package me.magic.plugintwo;

import me.magic.plugintwo.commands.*;
import me.magic.plugintwo.utils.Util;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;


public final class PluginTwo extends JavaPlugin {

    private static PluginTwo plugin;

    @Override
    public void onEnable() {

        plugin = this;

        Util.log("&aFixing all errors...");

        // Events
        String packageName = getClass().getPackage().getName();

        for (Class<?> clazz : new Reflections(packageName + ".listeners")
                .getSubTypesOf(Listener.class)
        ) {
            try {
                Listener listener = (Listener) clazz
                        .getDeclaredConstructor()
                        .newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Commands
        getCommand("fly").setExecutor(new Fly());
        getCommand("boom").setExecutor(new BoomCommand());
        getCommand("smeltcount").setExecutor(new SmeltCountCommand());
        getCommand("pv").setExecutor(new OpenCommand());
        getCommand("ping").setExecutor(new PingCommand());
    }

    public static PluginTwo getPlugin() {
        return plugin;

    }

    @Override
    public void onDisable() {
        Util.log("&cUnfixing all errors...");
    }
}