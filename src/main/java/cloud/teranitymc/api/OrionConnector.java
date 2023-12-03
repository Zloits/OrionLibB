package cloud.teranitymc.api;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrionConnector {
    private String username, password, database, host;
    private int port;
    private boolean ssl;

    private Connection connection;
    private HikariDataSource hikariDataSource;

    private boolean connected = false;
    private boolean connecting = false;

    public OrionConnector(String username, String password, String database, String host, int port, boolean ssl) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.host = host;
        this.port = port;
        this.ssl = ssl;
    }

    public void connect() throws SQLException {
        if (connection == null) {
            connecting = true;

            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);

            connecting = false;
            connected = true;
        }
    }

    public boolean isConnected() {
        return (connected != false);
    }

    public boolean next() {
        return (connecting != false);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public Connection getConnection() {
        return connection;
    }
}
