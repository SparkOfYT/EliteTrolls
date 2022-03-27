package de.sparkofyt.elitetrolls;

import de.sparkofyt.elitetrolls.commands.CommandBase;
import de.sparkofyt.elitetrolls.listeners.PlayerHandler;
import de.sparkofyt.elitetrolls.utils.config.ConfigManager;
import de.sparkofyt.elitetrolls.utils.config.LocalesConfig;
import de.sparkofyt.elitetrolls.utils.config.PermissionsConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EliteTrolls extends JavaPlugin {

    /* Variables*/
    private static EliteTrolls instance;
    public ConfigManager permissions;
    public ConfigManager locales;

    /* Methods */
    @Override
    public void onEnable() {
        instance = this;
        sendStartupMessage();
        initializeConfig();
        initializeCommands();
        initializeListener();
    }

    private void initializeConfig() {
        permissions = new ConfigManager(this, this.getDataFolder().getAbsolutePath()+"/", "permissions");
        locales = new ConfigManager(this, this.getDataFolder().getAbsolutePath()+"/", "locales");
        PermissionsConfig.setConfig();
        LocalesConfig.setConfig();
    }

    private void initializeListener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerHandler(), this);
    }

    private void initializeCommands() {
        CommandBase<EliteTrolls> trollsCMD = new CommandBase<EliteTrolls>(this) {
            @Override
            public boolean runCommand(CommandSender sender, Command rootCommand, String commandPrefix, String[] args) {
                if(!(sender instanceof Player)) return true;
                Player player = (Player) sender;
                return false;
            }
        };

        getCommand("elitetrolls").setExecutor(trollsCMD);
        getCommand("elitetrolls").setTabCompleter(trollsCMD);
    }

    private void sendStartupMessage() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§a  ___________.__  .__  __       ___________             .__  .__");
        Bukkit.getConsoleSender().sendMessage("§a  \\_   _____/|  | |__|/  |_  ___\\__    ___/______  ____ |  | |  |   ______");
        Bukkit.getConsoleSender().sendMessage("§a   |    __)_ |  | |  \\   __\\/ __ \\|    |  \\_  __ \\/  _ \\|  | |  |  /  ___/");
        Bukkit.getConsoleSender().sendMessage("§a   |        \\|  |_|  ||  | \\  ___/|    |   |  | \\(  <_> )  |_|  |__\\___ \\ ");
        Bukkit.getConsoleSender().sendMessage("§a  /_______  /|____/__||__|  \\___  >____|   |__|   \\____/|____/____/____  >");
        Bukkit.getConsoleSender().sendMessage("§a          \\/                    \\/                                     \\/       v1.0 by sparkofyt");
        Bukkit.getConsoleSender().sendMessage("");
    }

    /* Getters & Setters */
    public static EliteTrolls getInstance() {
        return instance;
    }
}
