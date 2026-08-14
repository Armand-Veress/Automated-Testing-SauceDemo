package utils;

public enum DefaultUserCredentials {
    STANDARD_USER("standard_user", "secret_sauce", "validFirstName", "validLastName", "1020");

    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    DefaultUserCredentials(String username, String password, String firstName, String lastName, String postalCode) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;

    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
