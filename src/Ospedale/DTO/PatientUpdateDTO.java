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
    long id;
    String firstname;
    String lastname;
    boolean gender;
    LocalDate birthdate;
    String address;
    long phone;
    String email;
    String username;
    String password;
    String confirmPassword;
}