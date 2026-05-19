/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

/**
 *
 * @author luddy
 */
public class DoctorUpdateDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String specialty;
    private String licenceNumber;
    private String assignedOffice;
    private String username;
    private String password;
    private String passwordconfirmation;

    public DoctorUpdateDTO(long id, String firstname, String lastname, String specialty, String licenceNumber, String assignedOffice, String username, String password, String passwordconfirmation) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialty = specialty;
        this.licenceNumber = licenceNumber;
        this.assignedOffice = assignedOffice;
        this.username = username;
        this.password = password;
        this.passwordconfirmation = passwordconfirmation;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordconfirmation() {
        return passwordconfirmation;
    }
    
}
