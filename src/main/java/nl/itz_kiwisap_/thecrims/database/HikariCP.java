package nl.itz_kiwisap_.thecrims.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nl.itz_kiwisap_.thecrims.user.CrimUser;
import org.bukkit.Bukkit;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HikariCP {

    HikariDataSource hikari;
    private String host, database, user, password, table;
    private int port;

    public HikariCP(String host, int port, String database, String user, String password, String table) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.table = table;

        init();
    }

    private void init() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database);
        config.setUsername(this.user);
        config.setPassword(this.password);
        config.addDataSourceProperty("useSSL", false);
        config.addDataSourceProperty("characterEncoding", "utf8");
        config.addDataSourceProperty("useUnicode", "true");
        config.addDataSourceProperty("allowPublicKeyRetrieval", "true");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        config.addDataSourceProperty("alwaysSendSetIsolation", "false");
        config.addDataSourceProperty("cacheCallableStmts", "true");
        config.validate();
        this.hikari = new HikariDataSource(config);
    }

    public void execute(String query, Object... params) {
        try (Connection connection = this.hikari.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            if(params != null)
                for(int i = 0; i < params.length; i++) statement.setObject((i + 1), params[i]);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query, Object... params) {
        try (Connection connection = this.hikari.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            if(params != null)
                for(int i = 0; i < params.length; i++) statement.setObject((i + 1), params[i]);
            CachedRowSet result = RowSetProvider.newFactory().createCachedRowSet();
            ResultSet resultSet = statement.executeQuery();
            result.populate(resultSet);
            resultSet.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean existUser(UUID uuid) {
        try {
            ResultSet resultSet = executeQuery(Query.EXISTS_USER(this.table), uuid.toString());
            if(resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CrimUser loadUser(UUID uuid) {
        if(!existUser(uuid)) {
            execute(Query.INSERT_USER(this.table), uuid.toString(), Bukkit.getPlayer(uuid).getName(), "");
            return new CrimUser(uuid);
        }

        try {
            CrimUser user = new CrimUser(uuid);
            ResultSet resultSet = executeQuery(Query.GET_USER(this.table), uuid.toString());

            if (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setData(CrimUser.dataFromString(resultSet.getString("data")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(CrimUser user) {
        execute(Query.UPDATE_USER(this.table), user.getName(), user.getData(), user.getUuid().toString());
    }

    public HikariDataSource getHikari() { return this.hikari; }
}
