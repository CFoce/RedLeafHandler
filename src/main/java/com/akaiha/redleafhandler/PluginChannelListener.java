package com.akaiha.redleafhandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginChannelListener implements PluginMessageListener {

	private Main plugin;
	
	public PluginChannelListener(Main plugin) {
		this.plugin = plugin;
	}
	
	
    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {
    	try {
        	DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            String subchannel = in.readUTF();
            Player player = plugin.getServer().getPlayer(UUID.fromString(insertDashUUID(in.readUTF())));
            if(subchannel.equals("perms")){
            	String input = in.readUTF();
                String[] info = input.split(",");
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                    	PermissionAttachment attachment = player.addAttachment(plugin);
                    	plugin.perms.put(player.getUniqueId(), attachment);
                    	for (int i = 0; i < info.length; i++) {
                    		attachment.setPermission(info[i], true);
                    	}
                    }
                }, 1L);
            }
            
            if(subchannel.equals("groups")){
            	String input = in.readUTF();
            	String[] info = input.split(",");
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                    	plugin.groups.put(player.getUniqueId(), "");
                    	for (int i = 0; i < info.length; i++) {
                    		plugin.groups.put(player.getUniqueId(), plugin.groups.get(player.getUniqueId()) + "[" + info[i] + "]");
                    	}
                    }
                }, 1L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String insertDashUUID(String uuid) {
    	StringBuffer sb = new StringBuffer(uuid);
    	sb.insert(8, "-");
    	 
    	sb = new StringBuffer(sb.toString());
    	sb.insert(13, "-");
    	 
    	sb = new StringBuffer(sb.toString());
    	sb.insert(18, "-");
    	 
    	sb = new StringBuffer(sb.toString());
    	sb.insert(23, "-");
    	 
    	return sb.toString();
    	}
}
