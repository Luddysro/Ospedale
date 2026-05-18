/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.DTO.DoctorCreateDTO;
import Ospedale.DTO.DoctorListDTO;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class DoctorController {

    private Storage storage;

    public DoctorController(Storage storage) {
        this.storage = storage;
    }

    public List<DoctorListDTO> getDoctors() {

        List<DoctorListDTO> doctors = new ArrayList<>();

        for (User user : storage.getUsers()) {
            if (user instanceof Doctor) {
                Doctor doctor = (Doctor) user;
                
                DoctorCreateDTO dto=new DoctorCreateDTO(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname(),
                doctor.getUsername(),
                doctor.getPassword(),
                
                doctor.getSpecialty(),
                doctor.getLicenceNumber(),
                doctor.getAssignedOffice()
                );
                
                
            }
        }

        return doctors;
    }
     public List<String> getDoctorNames() {
    List<String> options = new ArrayList<>();

    for (DoctorListDTO doc : getDoctors()) {
        options.add(doc.getId() + " " + doc.getFullName());
    }

    return options;
}
}
