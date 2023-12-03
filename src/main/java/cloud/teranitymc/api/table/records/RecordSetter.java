package cloud.teranitymc.api.table.records;

import cloud.teranitymc.api.OrionTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordSetter {
    private OrionTable orionTable;
    private Connection connection;

    private String setRecord;
    private String parentRecord;
    private Object setObject;
    private Object parentObject;

    private Object previousObject;

    private ResultSet resultSet;
    private boolean next;

    public RecordSetter(OrionTable orionTable) {
        if (!orionTable.exists()) return;

        this.orionTable = orionTable;
        this.connection = orionTable.getConnection();
    }

    public void setup() {
        if (connection == null) return;

        checkPreviousObject();
        try {
            String sql = "update " + orionTable.getTableName() + " set " + setRecord + " = ? where " + parentRecord + " = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, setObject);
            statement.setObject(2, parentObject);
            statement.executeUpdate();

            next = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkPreviousObject() {
        if (connection == null) return;

        try {
            String sql = "select " + setRecord + " from " + orionTable.getTableName() + " where " + parentRecord + " = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, parentObject);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                this.previousObject = resultSet.getObject(setRecord);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSetRecord() {
        return setRecord;
    }

    public void setSetRecord(String setRecord) {
        this.setRecord = setRecord;
    }

    public String getParentRecord() {
        return parentRecord;
    }

    public void setParentRecord(String parentRecord) {
        this.parentRecord = parentRecord;
    }

    public Object getSetObject() {
        return setObject;
    }

    public void setSetObject(Object setObject) {
        this.setObject = setObject;
    }

    public Object getParentObject() {
        return parentObject;
    }

    public void setParentObject(Object parentObject) {
        this.parentObject = parentObject;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Object getPreviousObject() {
        return previousObject;
    }

    public boolean next() {
        return next;
    }
}
