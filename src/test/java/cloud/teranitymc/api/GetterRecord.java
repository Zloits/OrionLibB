package cloud.teranitymc.api;

import cloud.teranitymc.api.table.records.RecordGetter;
import org.w3c.dom.ls.LSOutput;

public class GetterRecord {

    public static void main(String[] args) {
        RecordGetter recordGetter = new RecordGetter(ConnectSQL.getOrionTable());

        recordGetter.setRecordSelect("record2");
        recordGetter.setRecordParent("record1");
        recordGetter.setObjectParent((String) "anjay");

        recordGetter.setup();
        if (recordGetter.next()) {
            if (recordGetter.get() != null) {
                System.out.println(recordGetter.get());
            }
        }
    }
}
