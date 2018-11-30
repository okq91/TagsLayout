package okqapps.com.tagslayout;

public enum UnSelectedTagTheme {
    DARK(1),
    LIGHT(2),
    GRAY(3);

    private int typeID;

    UnSelectedTagTheme(int i) {
        this.typeID = i;
    }

    public static UnSelectedTagTheme fromInt(int i) {
        for (UnSelectedTagTheme type : UnSelectedTagTheme.values()) {
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