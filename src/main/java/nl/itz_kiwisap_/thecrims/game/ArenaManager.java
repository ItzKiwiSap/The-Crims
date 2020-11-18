package nl.itz_kiwisap_.thecrims.game;

import lombok.Getter;
import lombok.Setter;
import nl.itz_kiwisap_.thecrims.Main;
import nl.itz_kiwisap_.thecrims.game.teams.Team;
import nl.itz_kiwisap_.thecrims.user.CrimUser;
import nl.itz_kiwisap_.utils.bukkit.utils.configuration.ConfigFile;

import java.util.List;
import java.util.stream.Collectors;

public class ArenaManager {

    private Main plugin;
    @Getter @Setter private Arena arena;

    public ArenaManager(Main plugin) {
        this.plugin = plugin;
        this.arena = null;

        load();
    }

    private void load() {
        ConfigFile file = plugin.getResources().getArena();


    }

    public boolean isArenaSet() { return this.arena != null && this.arena.isReady(); }

    public List<CrimUser> getUsers() { return this.arena.getTeams().values().stream().flatMap(t -> t.getPlayers().stream()).collect(Collectors.toList()); }
    public Team getTeam(String name) { return this.arena.getTeams().getOrDefault(name, null); }
}
