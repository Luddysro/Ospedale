/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Services.DoctorService;

import Ospedale.DTO.DoctorCreateDTO;
import Ospedale.DTO.DoctorListDTO;

import Data.UserRepository;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.DoctorListDTO;
import Ospedale.DTO.DoctorUpdateDTO;

import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.User;
import Ospedale.Services.DoctorService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class DoctorController {

    private final DoctorService doctorService;
    private Storage storage;
    
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public DoctorController(Storage storage) {
        this(new DoctorService(new UserRepository(storage)));
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

    public Response updateDoctor(DoctorUpdateDTO dto){
     try {
            doctorService.updateDoctor(dto);
            return new Response("Doctor updated", Status.OK);
        } catch (IllegalArgumentException e) {
            return new Response("Passwords don't match", Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
}
    

    public List<DoctorListDTO> getDoctorList() {
        return doctorService.getDoctorList();
    }

}
