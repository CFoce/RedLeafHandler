package com.akaiha.perms.listener;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import com.akaiha.perms.Perms;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PlayerListener implements Listener {

	private Perms plugin;
	
	public PlayerListener(Perms plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLeave(PlayerQuitEvent event) {
		UUID uuid = event.getPlayer().getUniqueId();
		if (plugin.perms.containsKey(uuid)) {
			event.getPlayer().removeAttachment(plugin.perms.get(uuid));
			plugin.perms.remove(uuid);
		}
		if (plugin.groups.containsKey(uuid)) {
			plugin.groups.remove(uuid);
		}
    }
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		if(!plugin.jPlayer.containsKey(uuid)) {
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                	onPlayerJoin(event);
                }
            }, 10L);
			return;
		}
		JsonObject jObj = plugin.jPlayer.get(uuid);
		PermissionAttachment attachment = player.addAttachment(plugin);
    	plugin.perms.put(player.getUniqueId(), attachment);
    	if (jObj.has("perms")) {
    		JsonArray perms = jObj.getAsJsonArray("perms");
        	for (int i = 0; i < perms.size(); i++) {
        		attachment.setPermission(perms.get(i).getAsString(), true);
        	}
    	}
    	if (jObj.has("antiperms")) {
    		JsonArray antiPerms = jObj.getAsJsonArray("antiperms");
        	for (int i = 0; i < antiPerms.size(); i++) {
        		attachment.setPermission(antiPerms.get(i).getAsString(), false);
        	}
    	}
    	if (jObj.has("groups")) {
    		String groups = jObj.get("groups").getAsString();
        	plugin.groups.put(player.getUniqueId(), groups);
    	}
    	plugin.jPlayer.remove(uuid);
    }
}
