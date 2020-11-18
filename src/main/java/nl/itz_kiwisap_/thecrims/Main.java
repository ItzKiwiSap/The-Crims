package nl.itz_kiwisap_.thecrims;

import lombok.Getter;
import nl.itz_kiwisap_.thecrims.database.HikariCP;
import nl.itz_kiwisap_.thecrims.game.ArenaManager;
import nl.itz_kiwisap_.thecrims.listeners.Listeners;
import nl.itz_kiwisap_.thecrims.user.UserStorage;
import nl.itz_kiwisap_.thecrims.utils.configuration.Resources;
import nl.itz_kiwisap_.utils.bukkit.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Getter private static Main instance;

    @Getter private Resources resources;
    @Getter private HikariCP hikariCP;
    @Getter private ArenaManager arenaManager;
    @Getter private UserStorage userStorage;

    @Override
    public void onEnable() {
        instance = this;

        this.resources = new Resources(this);
        this.resources.load();

        if(this.resources.getConfig().getBoolean("database.enabled")) initDatabase();

        new Utils(this);

        this.arenaManager = new ArenaManager(this);
        this.userStorage = new UserStorage();

        Listeners.register();
    }

    @Override
    public void onDisable() {

    }

    private void initDatabase() {
        String host = this.resources.getConfig().getString("database.host");
        int port = this.resources.getConfig().getInt("database.port");
        String database = this.resources.getConfig().getString("database.database");
        String username = this.resources.getConfig().getString("database.username");
        String password = this.resources.getConfig().getString("database.password");
        String table = this.resources.getConfig().getString("database.table");
        this.hikariCP = new HikariCP(host, port, database, username, password, table);
    }
}
