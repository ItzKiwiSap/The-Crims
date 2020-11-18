package nl.itz_kiwisap_.thecrims.game;

import lombok.Getter;
import lombok.Setter;
import nl.itz_kiwisap_.thecrims.game.teams.Team;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Arena {

    private String name;
    private int gameTime;
    private Location waitingRoom;
    private Map<String, Team> teams = new HashMap<>();
    private int maxPlayers;

    private boolean ready;

    public Arena(String name, int gameTime) {
        this.name = name;
        this.gameTime = gameTime;
        this.waitingRoom = null;
    }
}
