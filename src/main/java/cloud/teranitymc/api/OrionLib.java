package cloud.teranitymc.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class OrionLib {
    private static OrionLib instance = new OrionLib();
    private Collection<Connection> connections = new ArrayList<>();
    private Connection connection;

    public OrionLib() {
    }

    public Connection connectSQL(OrionConnector orionConnector) {
        try {
            orionConnector.connect();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        if (orionConnector.isConnected()) {
            connection = orionConnector.getConnection();
            return connection;
        } return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Collection<Connection> getConnections() {
        return connections;
    }

    public static OrionLib getInstance() {
        return instance;
    }
}
