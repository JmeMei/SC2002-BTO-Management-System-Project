package BTOManagementSystem.Model.DAO.Enum;

public enum FlatType {
    NA("NA"),
    TWO_ROOM("2-Room"),
    THREE_ROOM("3-Room");

    private final String displayName;

    FlatType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FlatType fromString(String input) {
        for (FlatType type : FlatType.values()) {
            if (type.displayName.equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
