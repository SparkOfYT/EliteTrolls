package de.sparkofyt.elitetrolls.utils.config;

import de.sparkofyt.elitetrolls.EliteTrolls;

public class PermissionsConfig {

    /* Variables */
    public static String UPDATE_PERMISSION;

    /* Methods */
    public static void setConfig() {
        UPDATE_PERMISSION = EliteTrolls.getInstance().permissions.getStringOrSetDefault("update", "elitetrolls.update");
    }

}
