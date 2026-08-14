package utils;

public enum SortOptions {
    NAME_A_TO_Z("az"),
    NAME_Z_TO_A("za"),
    PRICE_LOW_TO_HIGH("lohi"),
    PRICE_HIGH_TO_LOW("hilo");

    private final String value;

    SortOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}