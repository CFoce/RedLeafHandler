package com.akaiha.redleafhandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
            if(subchannel.equals("perms")){
            	JsonParser parser = new JsonParser();
            	JsonObject jObj = parser.parse(in.readUTF()).getAsJsonObject();
            	Player player = plugin.getServer().getPlayer(UUID.fromString(jObj.get("uuid").getAsString()));
            	JsonArray perms = jObj.getAsJsonArray("perms");
            	JsonArray antiPerms = jObj.getAsJsonArray("antiperms");
            	String groups = jObj.get("groups").getAsString();
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                    	PermissionAttachment attachment = player.addAttachment(plugin);
                    	plugin.perms.put(player.getUniqueId(), attachment);
                    	for (int i = 0; i < perms.size(); i++) {
                    		attachment.setPermission(perms.get(i).getAsString(), true);
                    	}
                    	for (int i = 0; i < antiPerms.size(); i++) {
                    		attachment.setPermission(antiPerms.get(i).getAsString(), true);
                    	}
                    	plugin.groups.put(player.getUniqueId(), groups);
                    }
                }, 1L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
