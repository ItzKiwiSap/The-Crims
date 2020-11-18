package nl.itz_kiwisap_.thecrims.listeners.types;

import nl.itz_kiwisap_.thecrims.Main;
import nl.itz_kiwisap_.thecrims.user.CrimUser;
import nl.itz_kiwisap_.thecrims.utils.Placeholders;
import nl.itz_kiwisap_.thecrims.utils.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void on(PlayerLoginEvent event) {
        if(Settings.KICK_NOT_READY && !Main.getInstance().getArenaManager().isArenaSet() && !event.getPlayer().hasPermission("thecrims.setup")) {
            event.setKickMessage(Main.getInstance().getResources().format(false, "join.kick-not-ready"));
            event.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        CrimUser user = Main.getInstance().getHikariCP().loadUser(player.getUniqueId());

        if(user == null) {
            player.kickPlayer(Main.getInstance().getResources().format("join.kick-not-loaded"));
            return;
        } else {
            Main.getInstance().getUserStorage().addUser(user);
        }

        if(Main.getInstance().getArenaManager().isArenaSet()) event.setJoinMessage(Placeholders.replace(Main.getInstance().getResources().format("join.join-message"), player));
        else event.setJoinMessage(null);
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        if(Main.getInstance().getArenaManager().isArenaSet()) event.setQuitMessage(Placeholders.replace(Main.getInstance().getResources().format("quit.quit-message"), event.getPlayer()));
        else event.setQuitMessage(null);
    }
}
