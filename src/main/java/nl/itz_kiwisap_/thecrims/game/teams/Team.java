package nl.itz_kiwisap_.thecrims.game.teams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.itz_kiwisap_.thecrims.user.CrimUser;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.List;

@Getter
@AllArgsConstructor
public class Team {

    private String name;
    private ChatColor color;
    private List<CrimUser> players;

    private Location spawnLocation;
}
