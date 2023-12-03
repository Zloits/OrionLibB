package cloud.teranitymc.api;

import cloud.teranitymc.api.table.RecordType;

import java.sql.Connection;
import java.util.HashMap;

public class ConnectSQL {
    private static OrionLib instance = OrionLib.getInstance();
    private static Connection connection;

    private static OrionTable orionTable;

    public static void main(String[] args) {
        System.out.println("Reading SQL information...");
        OrionConnector orionConnector = new OrionConnector(
                "root",
                null,
                "spaceapi",
                "localhost",
                3306,
                false
        );

        if (orionConnector != null) {
            instance.connectSQL(orionConnector);
            System.out.println("Connected to SQL.");

            connection = instance.getConnection();
        }

        if (orionConnector.isConnected()) {
            orionTable = new OrionTable("test", connection);

            GetterRecord.main(args);
        }
    }

    public static OrionTable getOrionTable() {
        return orionTable;
    }
}
