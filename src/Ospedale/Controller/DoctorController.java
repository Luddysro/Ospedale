/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Data.UserRepository;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.DoctorListDTO;
import Ospedale.DTO.DoctorUpdateDTO;
import Ospedale.Model.User.Doctor;
import Ospedale.Services.DoctorService;
import java.util.List;

/**
 *
 * @author luddy
 */
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public DoctorController(Storage storage) {
        this(new DoctorService(new UserRepository(storage)));
    }
    
    public updateDoctor(DoctorUpdateDTO dto){
     try {
            DoctorService.updateDoctor(dto);
            return new Response("Doctor updated", Status.OK);
        } catch (IllegalArgumentException e) {
            return new Response("Passwords don't match", Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
}
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    public List<DoctorListDTO> getDoctorList() {
        return doctorService.getDoctorList();
    }
}
