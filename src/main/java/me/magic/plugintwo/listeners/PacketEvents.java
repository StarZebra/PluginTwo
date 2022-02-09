package me.magic.plugintwo.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.lang.reflect.InvocationTargetException;

public class PacketEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if(e.getBlock().getType() == Material.DIAMOND_ORE) {

            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.SET_ACTION_BAR_TEXT);
            packet.getChatComponents().write(0, WrappedChatComponent.fromText(ChatColor.AQUA + "Mined Dimond!"));
            try {
                manager.sendServerPacket(e.getPlayer(), packet);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    //@EventHandler
    //public void onMove(PlayerMoveEvent e) {

        //Player p = e.getPlayer(); //Bukkit
        //CraftPlayer craftPlayer = (CraftPlayer) p; //CraftBukkit
       // ServerPlayer serverPlayer = craftPlayer.getHandle(); //NMS

        //ServerGamePacketListenerImpl listener = serverPlayer.connection;

        //ClientboundGameEventPacket packet = new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0);
        //listener.send(packet);
        //serverPlayer.sendMessage(Component.nullToEmpty("Puffer fish stinging"), serverPlayer.getUUID());

    //}
}