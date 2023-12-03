package cloud.teranitymc.api;

import cloud.teranitymc.api.table.records.RecordInsert;

import java.util.UUID;

public class InsertCustomRecords {

    public static void main(String[] args) {
        RecordInsert recordInsert = new RecordInsert(ConnectSQL.getOrionTable());

        recordInsert.addRecord("record1", "record2");
        recordInsert.addObject((String) "woi", (Integer) 48928);

        if (recordInsert.isMatches()) {
            try {
                if (recordInsert.alreadyExists()) return;

                recordInsert.insert();

                System.out.println("Record inserted.");

                SetterRecords.main(args);
            }catch (Exception e) {
                System.out.println("Error. might not be insert.");
                e.printStackTrace();
            }
        }
    }
}
