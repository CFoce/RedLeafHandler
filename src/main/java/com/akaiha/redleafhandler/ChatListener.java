package com.akaiha.redleafhandler;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	private Main plugin;
	
	public ChatListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void setPrefix(AsyncPlayerChatEvent event) {
		if (plugin.groups.get(event.getPlayer().getUniqueId()) != null) {
			event.setFormat(ChatColor.WHITE + plugin.groups.get(event.getPlayer().getUniqueId()) + "<" + event.getPlayer().getDisplayName() + "> " + event.getMessage());
		}
	}
}
