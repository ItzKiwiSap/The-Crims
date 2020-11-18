package nl.itz_kiwisap_.thecrims.commands;

import nl.itz_kiwisap_.thecrims.Main;
import nl.itz_kiwisap_.thecrims.game.Arena;
import nl.itz_kiwisap_.thecrims.menus.setup.MainSetupMenu;
import nl.itz_kiwisap_.thecrims.utils.Utils;
import nl.itz_kiwisap_.utils.bukkit.CC;
import nl.itz_kiwisap_.utils.bukkit.commands.BaseCommand;
import nl.itz_kiwisap_.utils.bukkit.commands.annotation.*;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("thecrims|thecrim|crims|crim|tc")
@CommandPermission("thecrims.setup")
public class CrimCommand extends BaseCommand {

    @Default
    public void run(Player sender, @Single @Syntax("<name>") String name, @Syntax("<gameTime>") int gameTime) {
        Arena arena = new Arena(name, gameTime);
        Main.getInstance().getArenaManager().setArena(arena);

        sender.sendMessage(CC.translate("&aNew arena created with the name &b" + name + "&a, and a game time of " + Utils.millisToString(TimeUnit.SECONDS.toMillis(gameTime)) + "&a."));

        new MainSetupMenu(arena).openMenu(sender);
    }

    @Subcommand("setup")
    public void run(Player sender) {
        if(!Main.getInstance().getArenaManager().isArenaSet()) {
            sender.sendMessage(CC.translate("&cNo arena created yet. Create the arena with /thecrims <name> <gameTime>."));
            return;
        }
    }
}
