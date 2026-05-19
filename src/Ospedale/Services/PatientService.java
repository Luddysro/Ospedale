/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.UserRepository;
import Ospedale.DTO.PatientUpdateDTO;
import Ospedale.Model.User.Patient;
import java.time.LocalDate;

/**
 *
 * @author luddy
 */
public class PatientService {

    private final UserRepository userRepo;

    public PatientService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Patient updatePatient(PatientUpdateDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        Patient patient = userRepo.findPatientById(dto.getId());

        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }

        patient.setFirstname(dto.getFirstname());
        patient.setLastname(dto.getLastname());
        patient.setGender(parseGender(dto.getGender()));
        patient.setBirthdate(parseDate(dto.getBirthdate()));
        patient.setAddress(dto.getAddress());
        patient.setPhone(parsePhone(dto.getPhone()));
        patient.setEmail(dto.getEmail());
        patient.setUsername(dto.getUsername());
        patient.setPassword(dto.getPassword());

        return patient;
    }

    private boolean parseGender(String gender) {
        if ("Female".equals(gender)) {
            return true;
        }

        if ("Male".equals(gender)) {
            return false;
        }

        throw new IllegalArgumentException("Invalid gender");
    }

    private LocalDate parseDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid birthdate");
        }
    }

    private long parsePhone(String value) {
        try {
            return Long.parseLong(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid phone");
        }
    }
}
