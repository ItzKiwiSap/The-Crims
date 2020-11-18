package nl.itz_kiwisap_.thecrims.utils.configuration;

import lombok.Getter;
import nl.itz_kiwisap_.thecrims.Main;
import nl.itz_kiwisap_.utils.bukkit.CC;
import nl.itz_kiwisap_.utils.bukkit.utils.configuration.ConfigFile;

public class Resources {

    private Main plugin;
    @Getter private ConfigFile config;
    @Getter private ConfigFile messages;
    @Getter private ConfigFile arena;

    public Resources(Main plugin) {
        this.plugin = plugin;

        this.config = new ConfigFile(this.plugin, "config.yml");
        this.messages = new ConfigFile(this.plugin, "messages.yml");
        this.arena = new ConfigFile(this.plugin, "arena.yml");
    }

    public void load() {
        this.config.load();
        this.messages.load();
        this.arena.load();
    }

    public void save() {
        this.config.save();
        this.messages.save();
        this.arena.save();
    }

    public String format(String key) {
        return format(this.messages.getString("messages." + key).contains("%prefix%"), key);
    }

    public String format(boolean prefix, String key) {
        if(prefix) {
            String message = (this.messages.getString("messages." + key).contains("%prefix%")) ?
                    this.messages.getString("messages." + key).replaceAll("%prefix%", this.messages.getString("messages.prefix")) :
                    this.messages.getString("messages.prefix") + this.messages.getString("messages." + key);
            return CC.translate(message);
        } else {
            return CC.translate(this.messages.getString("messages." + key));
        }
    }

    public String prefix(String msg) {
        return CC.translate(this.messages.getString("messages.prefix") + msg);
    }
}
