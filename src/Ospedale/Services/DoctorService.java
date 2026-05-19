/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.UserRepository;
import Ospedale.DTO.DoctorListDTO;
import Ospedale.DTO.DoctorUpdateDTO;
import Ospedale.Model.Specialty;
import Ospedale.Model.User.Doctor;
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
    public Doctor updateDoctor(DoctorUpdateDTO dto){
           if (!dto.getPassword().equals(dto.getPasswordconfirmation())) {
            throw new IllegalArgumentException("Passwords don't match");
        }

        Doctor doctor = userRepo.findDoctorById(dto.getId());

        if (doctor == null) {
            throw new RuntimeException("Patient not found");
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

    if (specialty == null || specialty.isBlank()) {
        return null;
    }

    try {
        return Specialty.valueOf(
                specialty.trim().toUpperCase()
        );
    } catch (IllegalArgumentException e) {
        throw new RuntimeException(
                "Invalid specialty: " + specialty
        );
    }
}
}
