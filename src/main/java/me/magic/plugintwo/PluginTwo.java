package me.magic.plugintwo;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.magic.plugintwo.commands.BoomCommand;
import me.magic.plugintwo.commands.Fly;
import me.magic.plugintwo.events.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class PluginTwo extends JavaPlugin {

    private static PluginTwo plugin;

    @Override
    public void onEnable() {

        plugin = this;

        System.out.println("PluginTwo start!");

        // Events
        getServer().getPluginManager().registerEvents(new SnowballEvents(), this);
        getServer().getPluginManager().registerEvents(new GoldOreBreak(), this);
        getServer().getPluginManager().registerEvents(new ZombieSnowball(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);

        // Commands
        getCommand("fly").setExecutor(new Fly());
        getCommand("boom").setExecutor(new BoomCommand());


        //Creating a packet listener
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        //Listening for an Incoming packet - from the CLIENT to the SERVER
        manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Client.POSITION) {
            @Override
            public void onPacketReceiving(PacketEvent event) {

                PacketContainer packet = event.getPacket();
                Player p = event.getPlayer();

                //Extracting info from the packet
                double x = packet.getDoubles().read(0);
                double y = packet.getDoubles().read(1);
                double z = packet.getDoubles().read(2);;
                boolean isOnGround = packet.getBooleans().read(0);

                //p.sendMessage("INBOUND PACKET: x: " + x + " y: " + y + " z: " + z);
                //p.sendMessage("ON GROUND? " + isOnGround);

            }
        });

    }

    public static PluginTwo getPlugin() {
        return plugin;

    }

}