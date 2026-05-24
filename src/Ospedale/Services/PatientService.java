/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.UserRepository;
import Ospedale.DTO.PatientCreateDTO;
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

    public Patient createPatient(PatientCreateDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match");
        }
        long id = parseUserId(dto.getId(), "Invalid patient id");
        if (userRepo.findById(id) != null) {
            throw new IllegalArgumentException("User id already exists");
        }
        validateUniqueUsername(dto.getUsername(), id);
        validateEmail(dto.getEmail());

        Patient patient = new Patient(
                id,
                dto.getUsername(),
                dto.getFirstname(),
                dto.getLastname(),
                dto.getPassword(),
                dto.getEmail(),
                parseDate(dto.getBirthdate()),
                parseGender(dto.getGender()),
                parsePhone(dto.getPhone()),
                dto.getAddress()
        );
        userRepo.save(patient);
        return patient;
    }

    public Patient updatePatient(PatientUpdateDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        Patient patient = userRepo.findPatientById(dto.getId());

        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }
        validateUniqueUsername(dto.getUsername(), dto.getId());
        validateEmail(dto.getEmail());

        patient.setFirstname(dto.getFirstname());
        patient.setLastname(dto.getLastname());
        patient.setGender(parseGender(dto.getGender()));
        patient.setBirthdate(parseDate(dto.getBirthdate()));
        patient.setAddress(dto.getAddress());
        patient.setPhone(parsePhone(dto.getPhone()));
        patient.setEmail(dto.getEmail());
        patient.setUsername(dto.getUsername());
        if (!dto.getPassword().isEmpty()) {
            patient.setPassword(dto.getPassword());
        }
        userRepo.update(patient);

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
        if (value == null || !value.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone");
        }
        try {
            return Long.parseLong(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid phone");
        }
    }

    private long parseUserId(String value, String message) {
        if (value == null || !value.matches("\\d{12}")) {
            throw new IllegalArgumentException(message);
        }
        try {
            long id = Long.parseLong(value);
            if (id <= 0) {
                throw new IllegalArgumentException(message);
            }
            return id;
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.com$")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    private void validateUniqueUsername(String username, long currentUserId) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        if (userRepo.existsUsernameForAnotherUser(username, currentUserId)) {
            throw new IllegalArgumentException("Username already exists");
        }
    }
}
