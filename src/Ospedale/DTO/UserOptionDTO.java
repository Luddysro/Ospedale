package Ospedale.DTO;

public class UserOptionDTO {

    private final long id;
    private final String fullName;

    public UserOptionDTO(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return id + " - " + fullName;
    }
}
