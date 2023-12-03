package cloud.teranitymc.api;

import cloud.teranitymc.api.table.RecordType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OrionTable {
    private String tableName;
    private Connection connection;

    public OrionTable(String tableName, Connection connection) {
        this.connection = connection;
        this.tableName = tableName;

        if (!exists()) return;
    }

    public boolean exists() {
        return (exists(getTableName()) != false);
    }

    private boolean exists(String tableName) {
        if (connection == null) return false;

        try {
            String sql = "select * from " + tableName;
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            } return false;
        }catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    public String getTableName() {
        return tableName;
    }

    public Connection getConnection() {
        return connection;
    }
}
