package Ospedale.DTO;

public class DoctorCreateDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String confirmPassword;
    private String specialty;
    private String licenseNumber;
    private String assignedOffice;

    public DoctorCreateDTO(String id,
                           String firstname,
                           String lastname,
                           String username,
                           String password,
                           String confirmPassword,
                           String specialty,
                           String licenseNumber,
                           String assignedOffice) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.assignedOffice = assignedOffice;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }
}
