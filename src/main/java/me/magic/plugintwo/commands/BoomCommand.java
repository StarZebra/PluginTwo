package me.magic.plugintwo.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class BoomCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            ProtocolManager manager = ProtocolLibrary.getProtocolManager();

            player.getLineOfSight(null, 50).stream()
                    .filter(block -> block.getType() != Material.AIR)
                    .forEach(block -> {

                        Location blocklocation = block.getLocation();

                        PacketContainer packet = manager.createPacket(PacketType.Play.Server.EXPLOSION);
                        packet.getDoubles().write(0, blocklocation.getX());
                        packet.getDoubles().write(1, blocklocation.getY());
                        packet.getDoubles().write(2, blocklocation.getZ());
                        packet.getFloat().write(0, 5f);
                        packet.getFloat().write(1, 0f);
                        packet.getFloat().write(2, 2f);
                        packet.getFloat().write(3, 0f);

                        try {
                            manager.sendServerPacket(player, packet);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }


                    });
        }

        return true;
    }
}
