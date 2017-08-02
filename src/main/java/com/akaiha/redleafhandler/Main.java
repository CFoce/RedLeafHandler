package com.akaiha.redleafhandler;

import java.util.*;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.JsonObject;

public class Main extends JavaPlugin {

	public static PluginChannelListener pcl;
	public volatile Map<UUID, PermissionAttachment> perms;
	public volatile ConcurrentHashMap<UUID, String> groups;
	public volatile ConcurrentHashMap<UUID, JsonObject> jPlayer;

    @Override
    public void onEnable(){
    	perms = new HashMap<UUID, PermissionAttachment>();
    	groups = new ConcurrentHashMap<UUID, String>();
    	jPlayer = new ConcurrentHashMap<UUID, JsonObject>();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "Return", pcl = new PluginChannelListener(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }
}