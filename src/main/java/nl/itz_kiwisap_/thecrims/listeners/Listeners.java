package nl.itz_kiwisap_.thecrims.listeners;

import nl.itz_kiwisap_.thecrims.Main;
import nl.itz_kiwisap_.thecrims.listeners.types.PlayerJoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Listeners {

    public static void register() {
        registerListeners(new PlayerJoinQuitListener());
    }

    public static void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) registerListener(listener);
    }

    public static void registerListener(Listener listener) {
        try {
            Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
        } catch (Exception e) {
            System.out.println("Can not register listener /" + listener.getClass().getName());
            e.printStackTrace();
        }
    }
}
