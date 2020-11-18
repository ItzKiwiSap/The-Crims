package nl.itz_kiwisap_.thecrims.menus.setup;

import lombok.AllArgsConstructor;
import nl.itz_kiwisap_.thecrims.game.Arena;
import nl.itz_kiwisap_.thecrims.utils.Utils;
import nl.itz_kiwisap_.utils.bukkit.builders.ItemBuilder;
import nl.itz_kiwisap_.utils.bukkit.menu.Button;
import nl.itz_kiwisap_.utils.bukkit.menu.Menu;
import nl.itz_kiwisap_.utils.bukkit.menu.button.DisplayButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class MainSetupMenu extends Menu {

    private Arena arena;

    @Override
    public String getTitle(Player player) {
        return "&bThe Crims Setup";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        String gameTime = (arena.getGameTime() == 0) ? "&cNot set" : Utils.millisToString(TimeUnit.SECONDS.toMillis(arena.getGameTime()));
        String teams = (arena.getTeams().isEmpty()) ? "&cNot set" : "&b" + arena.getTeams().values().stream().map(t -> t.getColor() + t.getName()).toString().replace("[", "").replace("]", "");

        buttons.put(4, new DisplayButton(new ItemBuilder(Material.BOOK)
                .name("&bThe Crims | " + arena.getName())
                .lore("",
                        "&7Game time: " + gameTime,
                        "&7Teams: " + teams)
                .build(), true));

        return buttons;
    }
}
