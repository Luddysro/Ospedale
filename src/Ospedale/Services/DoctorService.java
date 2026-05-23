/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.UserRepository;
import Ospedale.DTO.DoctorCreateDTO;
import Ospedale.DTO.DoctorListDTO;
import Ospedale.DTO.DoctorUpdateDTO;
import Ospedale.DTO.UserOptionDTO;
import Ospedale.Model.Specialty;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class DoctorService {

    private final UserRepository userRepo;

    public DoctorService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<Doctor> getDoctors() {
        return userRepo.findDoctors();
    }

    public List<DoctorListDTO> getDoctorList() {
        List<DoctorListDTO> doctors = new ArrayList<>();

        for (Doctor doc : getDoctors()) {
            doctors.add(new DoctorListDTO(
                    doc.getId(),
                    doc.getFirstname() + " " + doc.getLastname()
            ));
        }

        return doctors;
    }

    public List<UserOptionDTO> getPatientOptions() {
        List<UserOptionDTO> patients = new ArrayList<>();

        for (Patient patient : userRepo.findPatients()) {
            patients.add(new UserOptionDTO(
                    patient.getId(),
                    patient.getFirstname() + " " + patient.getLastname()
            ));
        }

        return patients;
    }

    public Doctor createDoctor(DoctorCreateDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        long id = parseId(dto.getId(), "Invalid doctor id");

        if (userRepo.findById(id) != null) {
            throw new IllegalArgumentException("User id already exists");
        }

        Doctor doctor = new Doctor(
                id,
                dto.getUsername(),
                dto.getFirstname(),
                dto.getLastname(),
                dto.getPassword(),
                parseSpecialty(dto.getSpecialty()),
                dto.getLicenseNumber(),
                dto.getAssignedOffice()
        );

        userRepo.save(doctor);
        return doctor;
    }

    public Doctor getDoctorById(String value) {
        return userRepo.findDoctorById(parseId(value, "Invalid doctor"));
    }

    public Patient getPatientById(String value) {
        return userRepo.findPatientById(parseId(value, "Invalid patient"));
    }

    public Doctor updateDoctor(DoctorUpdateDTO dto){
           if (!dto.getPassword().equals(dto.getPasswordconfirmation())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        Doctor doctor = userRepo.findDoctorById(dto.getId());

        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }

        doctor.setFirstname(dto.getFirstname());
        doctor.setLastname(dto.getLastname());
        doctor.setAssignedOffice(dto.getAssignedOffice());
        doctor.setLicenceNumber(dto.getLicenceNumber());
        doctor.setSpecialty(parseSpecialty(dto.getSpecialty())); 
        doctor.setUsername(dto.getUsername());
        doctor.setPassword(dto.getPassword());

        return doctor;
    }
    private Specialty parseSpecialty(String specialty) {

    if (specialty == null || specialty.trim().isEmpty() || "Select one".equals(specialty)) {
        throw new IllegalArgumentException("Invalid specialty");
       }
    
    
    try {
        return Specialty.valueOf(specialty.replaceAll(" &", "").replaceAll(" ", "_").trim().toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid specialty: " + specialty);
    }
}

private long parseId(String value, String message) {
    try {
        return Long.parseLong(value);
    } catch (RuntimeException e) {
        throw new IllegalArgumentException(message);
    }
}
}
