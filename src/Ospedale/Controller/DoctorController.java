/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.DoctorCreateDTO;
import Ospedale.Model.Specialty;
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

    public Response createDoctor(DoctorCreateDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return new Response("Passwords don't match", Status.BAD_REQUEST);
        }

        try {
            long id = Long.parseLong(dto.getId());
            Specialty specialty = Specialty.valueOf(dto.getSpecialty().replaceAll(" &", "").replaceAll(" ", "_"));
            Doctor doctor = new Doctor(
                    id,
                    dto.getUsername(),
                    dto.getFirstname(),
                    dto.getLastname(),
                    dto.getPassword(),
                    specialty,
                    dto.getLicenseNumber(),
                    dto.getAssignedOffice()
            );
            storage.getUsers().add(doctor);
            return new Response("Doctor created", Status.CREATED);
        } catch (RuntimeException e) {
            return new Response("Invalid doctor information", Status.BAD_REQUEST);
        }
    }

    public List<Doctor> getDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        for (User user : storage.getUsers()) {
            if (user instanceof Doctor) {
                doctors.add((Doctor) user);
            }
        }

        return doctors;
    }
     public List<String> getDoctorNames() {
    List<String> names = new ArrayList<>();

    for (Doctor doc : getDoctors()) {
        names.add(doc.getFirstname() + " " + doc.getLastname());
    }

    return names;
}
}
