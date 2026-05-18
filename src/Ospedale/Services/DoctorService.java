/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.UserRepository;
import Ospedale.DTO.DoctorListDTO;
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
}
