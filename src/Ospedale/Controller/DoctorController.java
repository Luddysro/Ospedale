/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.DoctorCreateDTO;
import Ospedale.DTO.DoctorListDTO;
import Ospedale.DTO.DoctorUpdateDTO;
import Ospedale.DTO.UserOptionDTO;
import Ospedale.Model.User.Administrator;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import Ospedale.Services.DoctorService;
import Data.UserRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(Storage storage) {
        this(new DoctorService(new UserRepository(storage)));
    }

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public Response createDoctor(DoctorCreateDTO dto) {
        return new Response("Administrator privileges required", Status.BAD_REQUEST);
    }

    public Response createDoctor(DoctorCreateDTO dto, User currentUser) {
        if (!(currentUser instanceof Administrator)) {
            return new Response("Administrator privileges required", Status.BAD_REQUEST);
        }
        try {
            doctorService.createDoctor(dto);
            return new Response("Doctor created", Status.CREATED);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response("Invalid doctor information", Status.BAD_REQUEST);
        }
    }

     public List<String> getDoctorNames() {
        List<String> names = new ArrayList<>();
        for (DoctorListDTO doctor : getDoctorList()) {
            names.add(doctor.getFullName());
        }
        return names;
}

    public List<DoctorListDTO> getDoctorList() {
        return doctorService.getDoctorList();
    }

    public List<UserOptionDTO> getPatientOptions() {
        return doctorService.getPatientOptions();
    }

    public Response findDoctor(String id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor == null) {
                return new Response("Doctor not found", Status.NOT_FOUND);
            }
            java.util.HashMap<String, Object> data = new java.util.HashMap<>();
            data.put("doctor", doctor.serialize());
            data.put("doctorId", doctor.getId());
            return new Response("Doctor loaded", Status.OK, data);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        }
    }

    public Response findPatient(String id) {
        try {
            Patient patient = doctorService.getPatientById(id);
            if (patient == null) {
                return new Response("Patient not found", Status.NOT_FOUND);
            }
            java.util.HashMap<String, Object> data = new java.util.HashMap<>();
            data.put("patient", patient.serialize());
            data.put("patientId", patient.getId());
            return new Response("Patient loaded", Status.OK, data);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        }
    }

    public Response updateDoctor(DoctorUpdateDTO dto) {
        try {
            doctorService.updateDoctor(dto);
            return new Response("Doctor updated", Status.OK);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }
}
