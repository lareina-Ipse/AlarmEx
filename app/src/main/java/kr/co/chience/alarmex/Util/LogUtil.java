package kr.co.chience.alarmex.Util;

import android.util.Log;

public class LogUtil {

    private static final String LOG_PREFIX = "### ";
    public static final boolean LOG_ENABLE = true;

    public static void i(String Tag, String message) {
        if (LOG_ENABLE) {
            Log.i(Tag, LOG_PREFIX + message);
        }
    }

    public static void d(String Tag, String message) {
        if (LOG_ENABLE) {
            Log.d(Tag, LOG_PREFIX + message);
        }
    }

    public static void w(String Tag, String message) {
        if (LOG_ENABLE) {
            Log.w(Tag, LOG_PREFIX + message);
        }
    }

    public static void e(String Tag, String message) {
        if (LOG_ENABLE) {
            Log.e(Tag, LOG_PREFIX + message);
        }
    }
}
