package me.magic.plugintwo.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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
}
