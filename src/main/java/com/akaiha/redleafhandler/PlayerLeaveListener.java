package com.akaiha.redleafhandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

	private Main plugin;
	
	public PlayerLeaveListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
		plugin.perms.remove(event.getPlayer().getUniqueId());
		plugin.groups.remove(event.getPlayer().getUniqueId());
    }
}
