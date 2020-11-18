package nl.itz_kiwisap_.thecrims.user;

import de.simonsator.partyandfriends.api.party.PartyManager;
import de.simonsator.partyandfriends.api.party.PlayerParty;
import lombok.Getter;
import lombok.Setter;
import nl.itz_kiwisap_.thecrims.user.profile.UserStatistic;
import nl.itz_kiwisap_.utils.bukkit.utils.json.JSONConfig;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class CrimUser {

    private UUID uuid;
    private String name;
    private PlayerParty party;
    private HashMap<UserStatistic, Object> data;

    public CrimUser(UUID uuid) {
        this.uuid = uuid;
        this.name = "";
        this.party = (PartyManager.getInstance().getParty(uuid) != null) ? PartyManager.getInstance().getParty(uuid) : null;
        this.data = new HashMap<>();
    }

    public boolean inParty() { return this.party != null; }

    public void setStatistic(UserStatistic statistic, Object value) {
        if (statistic.getDefaultValue().equals(value)) this.data.remove(statistic);
        else this.data.put(statistic, value);
    }

    public String dataToString() {
        return new JSONObject(this.data).toJSONString();
    }

    public static HashMap<UserStatistic, Object> dataFromString(String string) {
        try {
            HashMap<UserStatistic, Object> data = new HashMap<>();
            JSONConfig config = new JSONConfig(string);

            for(UserStatistic statistic : UserStatistic.values()) {
                if(config.contains(statistic.name())) {
                    data.put(statistic, config.getObject(statistic.name()));
                }
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
