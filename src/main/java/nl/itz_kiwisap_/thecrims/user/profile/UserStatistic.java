package nl.itz_kiwisap_.thecrims.user.profile;

import lombok.Getter;
import nl.itz_kiwisap_.thecrims.user.CrimUser;

public enum UserStatistic {

    WINS(0),
    KILLS(0),
    GAMES_PLAYED(0);

    // Will be adding more in the future

    @Getter private Object defaultValue;

    UserStatistic(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    private Object getValue(CrimUser user) { return user.getData().getOrDefault(this, this.defaultValue); }

    public String getString(CrimUser user) {
        try {
            return (String) this.getValue(user);
        } catch (Exception e) {
            return (String) this.defaultValue;
        }
    }

    public boolean getBoolean(CrimUser user) {
        try {
            return (Boolean) this.getValue(user);
        } catch (Exception e) {
            return (Boolean) this.defaultValue;
        }
    }

    public int getInteger(CrimUser user) {
        try {
            return (Integer) this.getValue(user);
        } catch (Exception e) {
            return (Integer) this.defaultValue;
        }
    }
}
