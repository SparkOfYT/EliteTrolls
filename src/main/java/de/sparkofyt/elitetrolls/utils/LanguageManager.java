package de.sparkofyt.elitetrolls.utils;

import de.sparkofyt.elitetrolls.EliteTrolls;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager {

    /*
    * "uuid" : "locale"
    */

    /* Variables */
    private static final Map<Player, File> localeSettings = new HashMap<>();
    private static final Map<File, Map<String, String>> messages = new HashMap<>();

    /* Methods */
    public static void saveMessages() {
        File langFolder = new File(EliteTrolls.getInstance().getDataFolder() + "/locales");
        if(!langFolder.exists()) langFolder.mkdir();
        File defaultLanguage = new File(langFolder, "en.yml");
        try {
            if(!defaultLanguage.exists()) {
                InputStream is = EliteTrolls.getInstance().getResource("en.yml");
                if(is != null) Files.copy(is, defaultLanguage.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(File file : langFolder.listFiles()) {
            Map<String, String> localMessages = new HashMap<>();
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            for(String key : configuration.getKeys(false)) {
                for(String messageName : configuration.getConfigurationSection(key).getKeys(false)) {
                    String message = ChatColor.translateAlternateColorCodes('&', configuration.getString(key + "." + messageName));
                    localMessages.put(messageName, message);
                }
            }
            messages.put(file, localMessages);
        }
    }

    public static File getLocale(Player player) {
        return localeSettings.get(player);
    }

    public static void setLocale(Player player, File file) {
        if(!file.exists()) {
            File locale = new File(EliteTrolls.getInstance().getDataFolder() + "/locales", "en.yml");
            localeSettings.put(player, locale);
        } else {
            localeSettings.put(player, file);
        }
    }

    public static void removePlayer(Player player) {
        localeSettings.remove(player);
    }

    public static String getMessage(String locale, String path) {
        File file = new File(EliteTrolls.getInstance().getDataFolder() + "/locales",  locale + ".yml");
        return messages.get(file).get(path);
    }

    public static String getMessage(File locale, String path) {
        return messages.get(locale).get(path);
    }
}
