package ru.mctotalcraft.totalLib.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public abstract class AbstractCommand implements CommandExecutor {

    protected HashMap<String, SubCommand> commands;

    public AbstractCommand(PluginCommand pluginCommand, HashMap<String, SubCommand> commands) {
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
        }
        this.commands = commands;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;
        if (!(sender instanceof Player player)) return;
        SubCommand command = commands.get(args[0]);
        if (command instanceof PlayerRequired pCommand) pCommand.setPlayer(player);
        command.execute(args);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        execute(sender, args);
        return true;
    }


}