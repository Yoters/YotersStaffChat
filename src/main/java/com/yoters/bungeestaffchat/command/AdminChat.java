package com.yoters.bungeestaffchat.command;


import com.yoters.bungeestaffchat.BungeeStaffChat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdminChat extends Command {

    public AdminChat() {
        super("ac", null, "adminchat");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (BungeeStaffChat.SINGLETON.config.getBoolean("adminchat.enabled")) {

            if (!(sender instanceof ProxiedPlayer)) {
                ComponentBuilder message = new ComponentBuilder("You must be a ProxiedPlayer to do this!").color(ChatColor.RED);

                sender.sendMessage(message.create());

                return;
            }

            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (!(player.hasPermission("staffchat.admin") || player.hasPermission("*"))) {
                ComponentBuilder message = new ComponentBuilder("Insufficient permission.").color(ChatColor.RED);

                player.sendMessage(message.create());

                return;
            }

            if (args.length < 1) {
                ComponentBuilder message = new ComponentBuilder("Usage: /a [message]").color(ChatColor.RED);

                player.sendMessage(message.create());

                return;
            }

            StringBuilder builder = new StringBuilder();

            for (String argument : args) {
                builder.append(argument + " ");
            }

            for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
                if (proxiedPlayer.hasPermission("staffchat.admin") || proxiedPlayer.hasPermission("*")) {

                    String server = player.getServer().getInfo().getName();
                    String serverName = server.substring(0, 1).toUpperCase() + server.substring(1);

                    BaseComponent[] msg = new ComponentBuilder("(" + serverName + ")").color(ChatColor.DARK_AQUA)
                            .append("[AC]").color(ChatColor.RED)
                            .append(player.getName() + ":").color(ChatColor.WHITE)
                            .append(builder.toString()).color(ChatColor.RED)
                            .create();
                    proxiedPlayer.sendMessage(msg);
                }
            }
        }
    }
}
