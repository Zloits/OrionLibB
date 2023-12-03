package cloud.teranitymc.api.table.records;

import cloud.teranitymc.api.OrionTable;
import cloud.teranitymc.api.table.RecordType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RecordInsert {
    private OrionTable orionTable;
    private Connection connection;

    private ArrayList<String> records;
    private ArrayList<Object> insertObjects;

    private String query;

    public RecordInsert(OrionTable orionTable) {
        this.orionTable = orionTable;
        this.connection = orionTable.getConnection();

        records = new ArrayList<>();
        insertObjects = new ArrayList<>();
    }

    public void insert() {
        if (connection == null) return;

        try {
            StringBuilder strBuilder = new StringBuilder();
            StringBuilder recordsObject = new StringBuilder();

            for (String record : records) {
                strBuilder.append(record + ",");
            }
            for (Object obj : getInsertObjects()) {
                recordsObject.append("?,");
            }

            int length = strBuilder.length();
            length = length - 1;
            strBuilder.deleteCharAt(length);
            int length2 = recordsObject.length();
            length2 = length2 - 1;
            recordsObject.deleteCharAt(length2);

            query = "insert into " + orionTable.getTableName() + "(" + strBuilder.toString() + ") values (" + recordsObject.toString() + ")";
            PreparedStatement statement = connection.prepareStatement(query);

            for (Object obj : insertObjects) {
                int index = insertObjects.indexOf(obj) + 1;

                if (obj instanceof String) {
                    statement.setString(index, (String) obj);
                }else if (obj instanceof Integer) {
                    statement.setInt(index, (Integer) obj);
                }else if (obj instanceof Boolean) {
                    statement.setBoolean(index, (Boolean) obj);
                }
            }

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean alreadyExists() {
        if (connection == null) return true;

        try {
            String sql = "select * from " + orionTable.getTableName() + " where " + records.get(1) + " = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, insertObjects.get(1));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            } return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void addRecord(String... records) {
        for (String record : records) {
            this.records.add(record);
        }
    }

    public void addObject(Object... objects) {
        for (Object obj : objects) {
            insertObjects.add(obj);
        }
    }

    public boolean isMatches() {
        return (records.size() == insertObjects.size());
    }

    public ArrayList<Object> getInsertObjects() {
        return insertObjects;
    }

    public String getQuery() {
        return query;
    }

    public ArrayList<String> getRecords() {
        return records;
    }
}
