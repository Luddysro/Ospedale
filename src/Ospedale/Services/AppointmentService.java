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
import Ospedale.Model.AppointmentStatus;
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

public Appointment createAppointment(AppointmentCreateDTO dto) {

    Patient patient = userRepo.findPatientById(dto.getPatientId());
    Doctor doctor = userRepo.findDoctorById(dto.getDoctorId());

    if (patient == null) {
        throw new RuntimeException("Patient not found");
    }

    if (doctor == null) {
        throw new RuntimeException("Doctor not found");
    }

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
    patient.addAppointment(app);

    return app;
}
public List<AppointmentTableDTO> getAppointmentsByPatient(long id) {

List<Appointment> appointments = appointmentRepo.findByPatientId(id);

    List<AppointmentTableDTO> dtos = new ArrayList<>();

    for (Appointment a : appointments) {
        dtos.add(new AppointmentTableDTO(
            a.getId(),
            a.getDatetime().toString(),
            a.getDoctor().getFirstname() + " " + a.getDoctor().getLastname(),
            a.getSpecialty().name(),
            a.isType() ? "In-person" : "Remote",
            a.getStatus().name()
        ));
    }

    return dtos;
}
public void cancelAppointment(String idAppointment) {

    Appointment ap = appointmentRepo.findById(idAppointment);

    if (ap == null)
        throw new RuntimeException("Appointment not found");

    ap.setStatus(AppointmentStatus.CANCELED);
}

}