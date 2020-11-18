package nl.itz_kiwisap_.thecrims.database;

public class Query {

    public static String EXISTS_USER(String table) {
        return "SELECT name FROM " + table + " WHERE uuid=?";
    }

    public static String INSERT_USER(String table) {
        return "INSERT INTO " + table + " (uuid, name, data) VALUES (?, ?, ?)";
    }
    public static String GET_USER(String table) {
        return "SELECT * FROM " + table + " WHERE uuid=?";
    }

    public static String UPDATE_USER(String table) {
        return "UPDATE " + table + " SET name=?, data=? WHERE uuid=?";
    }

    public static String INSERT_TABLE(String table) {
        return "CREATE TABLE IF NOT EXISTS " + table + " (\n" +
                "id INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "uuid VARCHAR(255),\n" +
                "name VARCHAR(255),\n" +
                "data VARCHAR(255),\n" +
                "PRIMARY KEY (id)\n" +
                ");";
    }
}
