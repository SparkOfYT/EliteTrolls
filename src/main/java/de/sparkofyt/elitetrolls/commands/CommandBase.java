package de.sparkofyt.elitetrolls.commands;

import de.sparkofyt.elitetrolls.utils.Trolls;
import de.sparkofyt.elitetrolls.utils.config.MessagesConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public abstract class CommandBase<P extends Plugin> implements CommandExecutor, TabCompleter {

    /* Variables */
    private final P plugin;
    private final Map<String, CommandExecutor> subCommands = new HashMap<>();

    /* Constructor */
    public CommandBase(P plugin) {
        this.plugin = plugin;
    }

    /* Methods */
    public void registerSubCommand(String label, CommandExecutor subCommand) {
        subCommands.put(label.toLowerCase(), subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            CommandExecutor child = subCommands.get(args[0].toLowerCase());
            if (child != null) {
                label = args[0];
                String[] newArgs = new String[args.length - 1];
                System.arraycopy(args, 1, newArgs, 0, newArgs.length);
                return child.onCommand(sender, command, label, newArgs);
            }
        }
        return runCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            List<String> options = new ArrayList<>();
            for(Trolls troll : Trolls.values()) {
                options.add(troll.getName().toLowerCase());
            }
            return options;
        } else if (args.length == 2) {
            List<String> options = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()) {
                options.add(player.getName());
            }
            return options;
        }
        return new ArrayList<>();
    }

    /* Abstract Methods */
    public abstract boolean runCommand(CommandSender sender, Command rootCommand, String commandPrefix, String[] args);

    /* Getters & Setters */
    public P getPlugin() {
        return plugin;
    }
}
