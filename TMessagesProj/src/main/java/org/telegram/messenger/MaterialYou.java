package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

import org.telegram.ui.ActionBar.Theme;

/**
 * Material You (Material 3) helpers: on/off switch, dynamic colours taken from the system
 * wallpaper palette (Android 12+) and shared motion / shape constants.
 */
public class MaterialYou {

    public static final String PREF_ENABLED = "myou_enabled";

    private static Boolean enabledCache;

    public static boolean isEnabled() {
        if (enabledCache == null) {
            SharedPreferences prefs = MessagesController.getGlobalMainSettings();
            enabledCache = prefs.getBoolean(PREF_ENABLED, true);
        }
        return enabledCache;
    }

    public static void setEnabled(boolean enabled) {
        enabledCache = enabled;
        MessagesController.getGlobalMainSettings().edit().putBoolean(PREF_ENABLED, enabled).apply();
    }

    private static boolean isDark() {
        Theme.ThemeInfo info = Theme.getActiveTheme();
        return info != null && info.isDark();
    }

    /** Fill of the selection indicator ("secondary container" role). */
    public static int indicatorColor(Context context) {
        final boolean dark = isDark();
        if (Build.VERSION.SDK_INT >= 31) {
            return ContextCompat.getColor(context, dark ? android.R.color.system_accent2_700 : android.R.color.system_accent2_100);
        }
        final int base = Theme.getColor(Theme.key_windowBackgroundWhite);
        final int accent = Theme.getColor(Theme.key_glass_tabSelected);
        return ColorUtils.blendARGB(base, accent, dark ? 0.32f : 0.20f);
    }

    /** Colour of icon and label on the indicator ("on secondary container" role). */
    public static int onIndicatorColor(Context context) {
        final boolean dark = isDark();
        if (Build.VERSION.SDK_INT >= 31) {
            return ContextCompat.getColor(context, dark ? android.R.color.system_accent2_100 : android.R.color.system_accent2_900);
        }
        return Theme.getColor(Theme.key_glass_tabSelected);
    }

    /** Badge colour ("primary" role). */
    public static int primaryColor(Context context) {
        final boolean dark = isDark();
        if (Build.VERSION.SDK_INT >= 31) {
            return ContextCompat.getColor(context, dark ? android.R.color.system_accent1_200 : android.R.color.system_accent1_600);
        }
        return Theme.getColor(Theme.key_telegram_color);
    }

    /** Colour of text on top of the primary colour ("on primary" role). */
    public static int onPrimaryColor(Context context) {
        final boolean dark = isDark();
        if (Build.VERSION.SDK_INT >= 31) {
            return ContextCompat.getColor(context, dark ? android.R.color.system_accent1_800 : android.R.color.system_accent1_0);
        }
        return 0xFFFFFFFF;
    }
}
