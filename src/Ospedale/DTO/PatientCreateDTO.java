package Ospedale.DTO;

public class PatientCreateDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String gender;
    private String birthdate;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public PatientCreateDTO(String id, String firstname, String lastname, String gender, String birthdate,
                            String address, String phone, String email, String username,
                            String password, String confirmPassword) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getGender() { return gender; }
    public String getBirthdate() { return birthdate; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
}
