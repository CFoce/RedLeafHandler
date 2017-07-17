package com.akaiha.redleafhandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginChannelListener implements PluginMessageListener {

	private JavaPlugin plugin;
	
	public PluginChannelListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	
    @Override
    public void onPluginMessageReceived(String channel, final Player player, final byte[] message) {
    	try {
        	DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            
            String subchannel = in.readUTF();
            if(subchannel.equals("perms")){
            	String input = in.readUTF();
                String[] info = input.split(",");
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                    	player.sendMessage(input);
                    	PermissionAttachment attachment = player.addAttachment(plugin);
                    	Data.perms.put(player.getUniqueId(), attachment);
                    	for (int i = 0; i < info.length; i++) {
                    		attachment.setPermission(info[i], true);
                    	}
                    }
                }, 100L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
