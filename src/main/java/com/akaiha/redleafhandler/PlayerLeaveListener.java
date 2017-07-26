package com.akaiha.redleafhandler;

import java.util.UUID;

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
		UUID uuid = event.getPlayer().getUniqueId();
		event.getPlayer().removeAttachment(plugin.perms.get(uuid));
		plugin.perms.remove(uuid);
		plugin.groups.remove(uuid);
    }
}
