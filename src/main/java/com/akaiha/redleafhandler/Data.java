package com.akaiha.redleafhandler;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.permissions.PermissionAttachment;

public class Data {
	public static volatile ConcurrentHashMap<UUID, PermissionAttachment> perms = new ConcurrentHashMap<UUID, PermissionAttachment>();
}
