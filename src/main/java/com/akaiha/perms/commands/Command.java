package com.akaiha.perms.commands;

import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.akaiha.perms.Perms;

public class Command implements CommandExecutor {
	
	private Perms plugin;
	
	public Command(Perms plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if (args.length > 0 && sender.hasPermission("perms.perms.others")) {
			@SuppressWarnings("deprecation")
			Player p = plugin.getServer().getPlayer(args[0]);
			if (p == null) {
				return false;
			}
			PermissionAttachment pa = plugin.perms.get(p.getUniqueId());
			String str = "";
			for (Map.Entry<String, Boolean> entry : pa.getPermissions().entrySet()) {
				str += entry.getKey() + " = " + entry.getValue() + ", ";
			}
			sender.sendMessage(str);
			return false;
		}
		if (sender instanceof Player && sender.hasPermission("perms.perms")) {
			Player p = (Player) sender;
			PermissionAttachment pa = plugin.perms.get(p.getUniqueId());
			String str = "";
			for (Map.Entry<String, Boolean> entry : pa.getPermissions().entrySet()) {
				str += entry.getKey() + " = " + entry.getValue() + ", ";
			}
			p.sendMessage(str);
		}
		return false;
	}

}
