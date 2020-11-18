package nl.itz_kiwisap_.thecrims.utils;

import nl.itz_kiwisap_.thecrims.Main;
import org.bukkit.entity.Player;

public class Placeholders {

    public static String replace(String string, Player player) {
        String message;

        message = string.replaceAll("%player%", player.getName())
                .replaceAll("%current_players%", String.valueOf(Main.getInstance().getArenaManager().getUsers().size()))
                .replaceAll("%max_players%", String.valueOf(Main.getInstance().getArenaManager().getArena().getMaxPlayers()));

        return message;
    }
}
