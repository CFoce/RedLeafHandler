package com.akaiha.redleafhandler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

	@EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
		Data.perms.remove(event.getPlayer().getUniqueId());
    }
	
}
