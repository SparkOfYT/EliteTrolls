package de.sparkofyt.elitetrolls;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EliteTrolls extends JavaPlugin {

    /* Variables*/
    public static EliteTrolls instance;

    /* Methods */
    @Override
    public void onEnable() {
        instance = this;
        sendStartupMessage();
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
