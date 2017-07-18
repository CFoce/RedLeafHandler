package com.akaiha.redleafhandler;

import java.util.*;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static PluginChannelListener pcl;
	public volatile Map<UUID, PermissionAttachment> perms;
	public volatile ConcurrentHashMap<UUID, String> groups;

    @Override
    public void onEnable(){
    	perms = new HashMap<UUID, PermissionAttachment>();
    	groups = new ConcurrentHashMap<UUID, String>();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "Return", pcl = new PluginChannelListener(this));
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }
}