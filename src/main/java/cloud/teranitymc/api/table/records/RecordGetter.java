package cloud.teranitymc.api.table.records;

import cloud.teranitymc.api.OrionTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordGetter {
    private OrionTable orionTable;
    private Connection connection;

    private ResultSet resultSet;

    private boolean next;

    private String recordSelect;
    private String recordParent;
    private Object objectParent;

    public RecordGetter(OrionTable orionTable) {
        this.orionTable = orionTable;
        this.connection = orionTable.getConnection();
    }

    public void setup() {
        if (connection == null) return;

        try {
            String sql;

            if (recordParent == null && objectParent == null) {
                sql = "select " + recordSelect + " from " + orionTable.getTableName();
            }else {
                sql = "select " + recordSelect + " from " + orionTable.getTableName() + " where " + recordParent + " = ?";
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            if (objectParent != null) {
                statement.setObject(1, objectParent);
            }

            resultSet = statement.executeQuery();
            next = true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object get() {
        if (connection == null) return null;

        try {
            if (resultSet == null) return null;

            while (resultSet.next()) {
                return resultSet.getObject(getRecordSelect());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    public boolean exists() {
        if (get() != null) return true;

        return false;
    }

    public String getRecordSelect() {
        return recordSelect;
    }

    public void setRecordSelect(String recordSelect) {
        this.recordSelect = recordSelect;
    }

    public String getRecordParent() {
        return recordParent;
    }

    public void setRecordParent(String recordParent) {
        this.recordParent = recordParent;
    }

    public Object getObjectParent() {
        return objectParent;
    }

    public void setObjectParent(Object objectParent) {
        this.objectParent = objectParent;
    }

    public boolean next() {
        return (next != false);
    }
}
