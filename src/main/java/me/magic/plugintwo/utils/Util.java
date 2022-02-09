package me.magic.plugintwo.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");


    public static String getColString(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find()) {
            ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            String before = string.substring(0, matcher.start());
            String after = string.substring(matcher.end());
            string = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(string);
        }

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void log(String log) {
        Bukkit.getConsoleSender().sendMessage(getColString("&7[&bPlugin&3Two&7] " + log));
    }


}

