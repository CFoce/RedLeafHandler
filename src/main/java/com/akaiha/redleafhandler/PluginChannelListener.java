package com.akaiha.redleafhandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

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
            	UUID uuid = UUID.fromString(jObj.get("uuid").getAsString());
                plugin.jPlayer.put(uuid, jObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
