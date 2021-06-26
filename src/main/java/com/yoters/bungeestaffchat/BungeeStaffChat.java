package com.yoters.bungeestaffchat;

import com.yoters.bungeestaffchat.command.AdminChat;
import com.yoters.bungeestaffchat.command.StaffChat;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeStaffChat extends Plugin {
    @Override
    public void onEnable() {
        getLogger().info("Trying to start Metrics");

        getLogger().info("Metrics started successfully!");

        getProxy().getPluginManager().registerCommand(this, new StaffChat());
        getProxy().getPluginManager().registerCommand(this, new AdminChat());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
