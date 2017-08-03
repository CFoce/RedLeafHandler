package com.akaiha.redleafhandler;

import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class Command implements CommandExecutor {
	
	private Main plugin;
	
	public Command(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		Player p = (Player) sender;
		PermissionAttachment pa = plugin.perms.get(p.getUniqueId());
		String str = "";
		for (Map.Entry<String, Boolean> entry : pa.getPermissions().entrySet()) {
			str += entry.getKey() + " = " + entry.getValue() + ", ";
		}
		p.sendMessage(str);
		return false;
	}

}
