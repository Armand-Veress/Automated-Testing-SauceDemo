package utils;

public enum ErrorMessages {
    ACCESS_DENIED_TEMPLATE("Epic sadface: You can only access '%s' when you are logged in.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}