package nl.itz_kiwisap_.thecrims.game.teams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Team {

    private String name;
    private ChatColor color;
    private List<UUID> players;

    private Location spawnLocation;
}
