package com.akaiha.perms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import com.akaiha.core.data.network.Receive;
import com.akaiha.perms.commands.Command;
import com.akaiha.perms.listener.ChatListener;
import com.akaiha.perms.listener.PlayerListener;
import com.akaiha.perms.receiver.Receiver;
import com.google.gson.JsonObject;

public class Perms extends JavaPlugin {
	
	public volatile Map<UUID, PermissionAttachment> perms;
	public volatile ConcurrentHashMap<UUID, String> groups;
	public volatile ConcurrentHashMap<UUID, JsonObject> jPlayer;

    @Override
    public void onEnable(){
    	perms = new HashMap<UUID, PermissionAttachment>();
    	groups = new ConcurrentHashMap<UUID, String>();
    	jPlayer = new ConcurrentHashMap<UUID, JsonObject>();
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        Receive.registerCommand("perms", new Receiver(this));
        getCommand("perms").setExecutor(new Command(this));
    }
}