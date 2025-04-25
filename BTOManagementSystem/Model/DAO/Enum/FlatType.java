package BTOManagementSystem.Model.DAO.Enum;

/**
 * Enumeration representing the types of flats available in the BTO Management System.
 * <p>
 * Each enum constant has a corresponding display name used for user-friendly output.
 * </p>
 *
 * <p>Available flat types:</p>
 * <ul>
 *   <li><b>NA</b> – Not applicable or not specified</li>
 *   <li><b>TWO_ROOM</b> – 2-Room flat</li>
 *   <li><b>THREE_ROOM</b> – 3-Room flat</li>
 * </ul>
 */
public enum FlatType {
    NA("NA"),
    TWO_ROOM("2-Room"),
    THREE_ROOM("3-Room");

    private final String displayName;

    /**
     * Constructs a {@code FlatType} with a display name.
     *
     * @param displayName the display name associated with the flat type
     */
    FlatType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the flat type.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the corresponding {@code FlatType} for a given display name.
     *
     * @param input the display name to match
     * @return the matching {@code FlatType}, or {@code null} if no match is found
     */
    public static FlatType fromString(String input) {
        for (FlatType type : FlatType.values()) {
            if (type.displayName.equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
