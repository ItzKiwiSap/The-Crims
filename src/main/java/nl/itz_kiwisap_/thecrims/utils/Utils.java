package nl.itz_kiwisap_.thecrims.utils;

import nl.itz_kiwisap_.utils.bukkit.CC;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static String millisToString(long millis) {
        if (millis < 1000) return String.format("0 %s", "s");

        String[] units = { "d", "h", "m", "s" };
        long[] times = new long[4];

        times[0] = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
        millis -= TimeUnit.MILLISECONDS.convert(times[0], TimeUnit.DAYS);
        times[1] = TimeUnit.HOURS.convert(millis, TimeUnit.MILLISECONDS);
        millis -= TimeUnit.MILLISECONDS.convert(times[1], TimeUnit.HOURS);
        times[2] = TimeUnit.MINUTES.convert(millis, TimeUnit.MILLISECONDS);
        millis -= TimeUnit.MILLISECONDS.convert(times[2], TimeUnit.MINUTES);
        times[3] = TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS);

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            if (times[i] > 0) {
                s.append(String.format(CC.translate("&b%d%s&7, &b"), times[i], units[i]));
            }
        }
        return s.substring(0, s.length() - 4);
    }
}
