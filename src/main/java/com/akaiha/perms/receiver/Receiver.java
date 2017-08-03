package com.akaiha.perms.receiver;

import java.util.UUID;

import com.akaiha.core.data.network.ReceiveCommand;
import com.akaiha.perms.Perms;
import com.google.gson.JsonObject;

public class Receiver implements ReceiveCommand {

	private Perms plugin;
	
	public Receiver(Perms plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void execute(JsonObject jObj) {
		if (jObj.has("uuid")) {
			UUID uuid = UUID.fromString(jObj.get("uuid").getAsString());
	        plugin.jPlayer.put(uuid, jObj);
		}
	}

}
