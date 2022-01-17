package me.magic.plugintwo.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.lang.reflect.InvocationTargetException;

public class PacketEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if(e.getBlock().getType() == Material.DIAMOND_ORE) {

            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.SET_ACTION_BAR_TEXT);
            packet.getModifier().writeDefaults();
            packet.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.AQUA + "Mined Dimond!"));
            try {
                manager.sendServerPacket(e.getPlayer(), packet);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onEnderPearl(ProjectileLaunchEvent e) {

        if(e.getEntity() instanceof Egg) {

            //Doesnt add cooldown

            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.SET_COOLDOWN);
            packet.getModifier().writeDefaults();
            packet.getIntegers().write(0, 82).write(0, 100);
            try {
                manager.sendServerPacket((Player) e.getEntity().getShooter(), packet);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }

        }

    }
}
