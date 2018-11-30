package okqapps.com.tagslayout;

public enum TagTextSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private int typeID;

    TagTextSize(int i) {
        this.typeID = i;
    }

    public static TagTextSize fromInt(int i) {
        for (TagTextSize type : TagTextSize.values()) {
            if (type.getMode() == i) {
                return type;
            }
        }
        return null;
    }

    public int getMode() {
        return typeID;
    }
}