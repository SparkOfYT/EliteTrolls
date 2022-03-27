package de.sparkofyt.elitetrolls.utils.config;

import de.sparkofyt.elitetrolls.EliteTrolls;
import org.bukkit.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.List;

public class ConfigManager {

    /* Variables */
    Plugin plugin;
    File file;
    FileConfiguration configuration;
    String fileDirectory;
    String fileName;
    String startPath = "";

    /* Constructor */
    public ConfigManager(Plugin plugin, String name) {
        this.plugin = plugin;
        this.fileName = name.replace(".yml","");
        this.fileDirectory = plugin.getDataFolder().getAbsolutePath();
        this.file = new File(this.fileDirectory + this.fileName + ".yml");

        init();
    }

    public ConfigManager(Plugin plugin, String dir, String name) {
        this.plugin = plugin;
        this.fileName = name.replace(".yml","");
        this.fileDirectory = dir;
        this.file = new File(dir + this.fileName + ".yml");

        init();
    }

    /* Methods */
    private void init() {
        File dir = new File(this.fileDirectory);
        if (!dir.exists()) dir.mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStartPath(String path){
        if (!path.endsWith(".")){
            this.startPath = path+".";
        }else{
            this.startPath = path;
        }
    }

    /* Getters & Setters */
    public void set(String path, Object object){
        path = startPath + path;
        configuration.set(path,object);
        save();
    }

    public Object get(String path){
        path = startPath + path;
        return configuration.get(path);
    }

    public Object getOrSetDefault(String path, Object defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path,defaultValue);
            save();
            return defaultValue;
        }
        return configuration.get(path);
    }

    public String getString(String path) {
        path = startPath + path;
        return configuration.getString(path);
    }

    public String getStringOrSetDefault(String path, String defaultValue) {
        path = startPath + path;
        if(!configuration.contains(path)) {
            configuration.set(path, defaultValue);
            save();
            return defaultValue;
        }
        return configuration.getString(path);
    }

    public int getInt(String path){
        path = startPath + path;
        return configuration.getInt(path);
    }

    public int getIntOrSetDefault(String path, int defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path, defaultValue);
            save();
            return defaultValue;
        }
        return configuration.getInt(path);
    }

    public double getDouble(String path){
        path = startPath + path;
        return configuration.getDouble(path);
    }

    public double getDoubleOrSetDefault(String path, double defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path, defaultValue);
            save();
            return defaultValue;
        }
        return configuration.getDouble(path);
    }

    public boolean getBoolean(String path){
        path = startPath + path;
        return configuration.getBoolean(path);
    }

    public boolean getBooleanOrSetDefault(String path, boolean defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path, defaultValue);
            save();
            return defaultValue;
        }
        return configuration.getBoolean(path);
    }

    public List<?> getList(String path){
        path = startPath + path;
        return configuration.getList(path);
    }
    public List<?> getListOrSetDefault(String path, List<?> defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path, defaultValue);
            save();
            return defaultValue;
        }
        return getList(path);
    }

    public Location getLocation(String path) {
        path = startPath+path;
        if (getString(String.format("%s.world", path)) == null) return null;

        World world = Bukkit.getWorld(getString(String.format("%s.world", path)));
        double x = getDouble(String.format("%s.x", path));
        double y = getDouble(String.format("%s.y", path));
        double z = getDouble(String.format("%s.z", path));
        float yaw = (float) getDouble(String.format("%s.yaw", path));
        float pitch = (float) getDouble(String.format("%s.pitch", path));
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Location getLocationOrSetDefault(String path, Location defaultValue) {
        path = startPath+path;
        if(!configuration.contains(path)) {
            configuration.set(String.format("%s.world", path), defaultValue.getWorld());
            configuration.set(String.format("%s.x", path), defaultValue.getX());
            configuration.set(String.format("%s.y", path), defaultValue.getY());
            configuration.set(String.format("%s.z", path), defaultValue.getZ());
            configuration.set(String.format("%s.yaw", path), (double) defaultValue.getYaw());
            configuration.set(String.format("%s.pitch", path), (double) defaultValue.getPitch());
            save();
            return defaultValue;
        }
        return getLocation(path);
    }

    public Color getColor(String path){
        path = startPath + path;
        return Color.fromRGB((Integer) configuration.get(path));
    }

    public Color getColorOrSetDefault(String path, Color defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path, defaultValue.asRGB());
            save();
            return defaultValue;
        }
        return Color.fromRGB((Integer) configuration.get(path));
    }

    public ChatColor getChatColor(String path){
        path = startPath + path;
        return ChatColor.valueOf(getString(path));
    }

    public ChatColor getChatColorOrSetDefault(String path, ChatColor defaultValue){
        path = startPath + path;
        if (!configuration.contains(path)){
            configuration.set(path, defaultValue.name());
            save();
            return defaultValue;
        }
        return ChatColor.getByChar(configuration.getString(path));
    }

    public File getFile() {
        return file;
    }
    public FileConfiguration getConfiguration() {
        return configuration;
    }
    public String getFileName() {
        return fileName;
    }
    public String getFileDirectory() {
        return fileDirectory;
    }
    public String getStartPath() {
        return startPath;
    }
}
