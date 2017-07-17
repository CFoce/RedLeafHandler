package com.akaiha.redleafhandler;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static PluginChannelListener pcl;

    @Override
    public void onEnable(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "Return", pcl = new PluginChannelListener(this));
    }

}