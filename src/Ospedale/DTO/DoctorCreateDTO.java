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
    long id;
    String firstname;
    String lastname;
    String username;
    String password;
    Specialty specialty;
    String licenseNumber;
    String assignedOffice;
}