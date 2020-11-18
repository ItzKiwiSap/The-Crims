package nl.itz_kiwisap_.thecrims.user;

import java.util.HashMap;
import java.util.UUID;

public class UserStorage {

    public HashMap<UUID, CrimUser> loaded = new HashMap<>();

    public void addUser(CrimUser user) { this.loaded.put(user.getUuid(), user); }

    public CrimUser getUser(UUID uuid) {
        return this.loaded.getOrDefault(uuid, null);
    }
}
