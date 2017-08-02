package com.akaiha.redleafhandler;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	private Main plugin;
	
	public ChatListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void setPrefix(AsyncPlayerChatEvent event) {
		if (plugin.groups.containsKey(event.getPlayer().getUniqueId())) {
			event.setFormat(ChatColor.translateAlternateColorCodes('&', (plugin.groups.get(event.getPlayer().getUniqueId()) + " &F" + event.getPlayer().getDisplayName() + ": &7" + event.getMessage())));
		} else {
			event.setFormat(ChatColor.translateAlternateColorCodes('&', ("&7" + event.getPlayer().getDisplayName() + "&F: &7" + event.getMessage())));
		}
	}
}
