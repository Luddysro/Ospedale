/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

import Ospedale.Model.Specialty;

/**
 *
 * @author luddy
 */
public class DoctorCreateDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Specialty specialty;
    private String licenseNumber;
    private String assignedOffice;

    public DoctorCreateDTO(long id, String firstname, String lastname, String username, String password, Specialty specialty, String licenseNumber, String assignedOffice) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.assignedOffice = assignedOffice;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }
    
}