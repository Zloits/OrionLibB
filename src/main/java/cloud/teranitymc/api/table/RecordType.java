package cloud.teranitymc.api.table;

public enum RecordType {
    STRING("varchar"),
    BOOLEAN("boolean"),
    INTEGER("int");

    private String name;

    RecordType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
