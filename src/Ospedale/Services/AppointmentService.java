/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.AppointmentRepo;
import Data.Storage;
import Data.UserRepository;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.AppointmentTableDTO;
import Ospedale.DTO.AppointmentCreateDTO;
import Ospedale.Model.Appointment;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author luddy
 */
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final UserRepository userRepo;

    public AppointmentService(AppointmentRepo appointmentRepo,
                              UserRepository userRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
    }

    public Response createAppointment(AppointmentCreateDTO dto) {

        Patient patient = userRepo.findPatientById(dto.getPatientId());
        Doctor doctor = userRepo.findDoctorById(dto.getDoctorId());

        if (patient == null)
            return new Response("Patient not found", Status.NOT_FOUND);

        if (doctor == null)
            return new Response("Doctor not found", Status.NOT_FOUND);

        String id = "APP-" + UUID.randomUUID();

        Appointment app = new Appointment(
            id,
            patient,
            doctor,
            doctor.getSpecialty(),
            dto.getDatetime(),
            dto.getReason(),
            dto.isType()
        );

        appointmentRepo.save(app);
        patient.getAppointments().add(app);

        return new Response("Appointment created", Status.CREATED);
    }

    public List<AppointmentTableDTO> getByPatient(long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }
}