package cloud.teranitymc.api;

import cloud.teranitymc.api.table.records.RecordSetter;

public class SetterRecords {
    private static OrionLib orionLib = new OrionLib();

    public static void main(String[] args) {
        RecordSetter recordSetter = new RecordSetter(ConnectSQL.getOrionTable());

        recordSetter.setSetRecord("record2");
        recordSetter.setParentRecord("record1");

        recordSetter.setSetObject((Integer) 100);
        recordSetter.setParentObject((String) "anjay");

        recordSetter.setup();
        if (recordSetter.next()) {
            System.out.println("New value: " + recordSetter.getSetObject());
            System.out.println("Old value: " + recordSetter.getPreviousObject());
        }
    }
}
