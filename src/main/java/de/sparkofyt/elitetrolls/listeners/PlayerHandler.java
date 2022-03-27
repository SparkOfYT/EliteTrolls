package de.sparkofyt.elitetrolls.listeners;

import de.sparkofyt.elitetrolls.EliteTrolls;
import de.sparkofyt.elitetrolls.utils.LanguageManager;
import de.sparkofyt.elitetrolls.utils.UpdateChecker;
import de.sparkofyt.elitetrolls.utils.config.PermissionsConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.UUID;

public class PlayerHandler implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if(player.hasPermission(PermissionsConfig.UPDATE_PERMISSION)) {
            new UpdateChecker(EliteTrolls.getInstance(), 12345).getVersion(version -> {
                if (EliteTrolls.getInstance().getDescription().getVersion().equals(version)) {
                    EliteTrolls.getInstance().getLogger().info("EliteTrolls: There is not a new update available.");
                } else {
                    EliteTrolls.getInstance().getLogger().info("EliteTrolls: There is a new update available.");
                }
            });
        }

        if(EliteTrolls.getInstance().locales.getString(uuid.toString()) == null) {
            LanguageManager.setLocale(player, new File(EliteTrolls.getInstance().getDataFolder() + "/locales", "en.yml"));
            return;
        }

        String localeFileName = EliteTrolls.getInstance().locales.getString(uuid.toString());
        File langFile = new File(EliteTrolls.getInstance().getDataFolder() + "/locales", localeFileName + ".yml");
        LanguageManager.setLocale(player, langFile);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

    }
}
