/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

import java.time.LocalDate;

/**
 *
 * @author luddy
 */
public class PatientUpdateDTO {
    private long id;
    private String firstname;
    private String lastname;
    private boolean gender;
    private LocalDate birthdate;
    private String address;
    private long phone;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean isGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}