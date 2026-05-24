/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.AppointmentRepo;
import Data.UserRepository;
import Ospedale.DTO.AppointmentTableDTO;
import Ospedale.DTO.AppointmentCreateDTO;
import Ospedale.Model.Appointment;
import Ospedale.Model.AppointmentStatus;
import Ospedale.Model.Prescription;
import Ospedale.Model.Specialty;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    Doctor doctor = resolveDoctor(dto.getDoctorId());

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
        parseDateTime(dto.getDate(), dto.getTime()),
        dto.getReason(),
        parseType(dto.getType())
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

public List<AppointmentTableDTO> getAppointmentsByDoctor(long id, AppointmentStatus status) {
    List<Appointment> appointments = appointmentRepo.findByDoctorId(id);
    List<AppointmentTableDTO> dtos = new ArrayList<>();

    for (Appointment a : appointments) {
        if (status == null || a.getStatus().equals(status)) {
            dtos.add(new AppointmentTableDTO(
                a.getId(),
                a.getDatetime().toString(),
                a.getPatient().getFirstname() + " " + a.getPatient().getLastname(),
                a.getSpecialty().name(),
                a.isType() ? "In-person" : "Remote",
                a.getStatus().name()
            ));
        }
    }

    return dtos;
}

public void acceptAppointment(String idAppointment) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    appointment.setStatus(AppointmentStatus.PENDING);
}

public void completeAppointment(String idAppointment, String diagnosis, String observations,
                                String recommendedTreatment, String followUp) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    appointment.setStatus(AppointmentStatus.COMPLETED);
    appointment.setDiagnosis(diagnosis);
    appointment.setObservations(observations);
    appointment.setRecommendedTreatment(recommendedTreatment);
    appointment.setFollowUp(followUp);
}

public void rescheduleAppointment(String idAppointment, String time, String reason) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    try {
        appointment.setDatetime(LocalDateTime.of(appointment.getDatetime().toLocalDate(), LocalTime.parse(time)));
    } catch (RuntimeException e) {
        throw new IllegalArgumentException("Invalid appointment time");
    }
    appointment.setReason(reason);
}

public Prescription addPrescription(String idAppointment, String medicationName, String dose,
                                    String administrationRoute, String treatmentDuration,
                                    String additionalInformation, String frequency) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    Prescription prescription = new Prescription(
            appointment,
            medicationName,
            parseDouble(dose, "Invalid dose"),
            administrationRoute,
            parseInteger(treatmentDuration, "Invalid treatment duration"),
            additionalInformation,
            parseInteger(frequency, "Invalid frequency")
    );
    appointment.addPrescription(prescription);
    return prescription;
}

private Appointment findAppointmentOrThrow(String idAppointment) {
    Appointment appointment = appointmentRepo.findById(idAppointment);

    if (appointment == null) {
        throw new RuntimeException("Appointment not found");
    }

    return appointment;
}

private int parseInteger(String value, String message) {
    try {
        return Integer.parseInt(value);
    } catch (RuntimeException e) {
        throw new IllegalArgumentException(message);
    }
}

private double parseDouble(String value, String message) {
    try {
        return Double.parseDouble(value);
    } catch (RuntimeException e) {
        throw new IllegalArgumentException(message);
    }
}

private long parseId(String value, String message) {
    try {
        return Long.parseLong(value);
    } catch (RuntimeException e) {
        throw new IllegalArgumentException(message);
    }
}

private Doctor resolveDoctor(String value) {
    try {
        return userRepo.findDoctorById(parseId(value, "Invalid doctor"));
    } catch (IllegalArgumentException e) {
        Specialty specialty = parseSpecialty(value);
        Doctor doctor = userRepo.findDoctorBySpecialty(specialty);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found for specialty");
        }
        return doctor;
    }
}

private Specialty parseSpecialty(String value) {
    try {
        return Specialty.valueOf(value.replaceAll(" &", "").replaceAll(" ", "_").trim().toUpperCase());
    } catch (RuntimeException e) {
        throw new IllegalArgumentException("Invalid doctor or specialty");
    }
}

private LocalDateTime parseDateTime(String date, String time) {
    try {
        return LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    } catch (RuntimeException e) {
        throw new IllegalArgumentException("Invalid appointment date or time");
    }
}

private boolean parseType(String type) {
    if ("In-person".equals(type)) {
        return true;
    }

    if ("Remote".equals(type)) {
        return false;
    }

    throw new IllegalArgumentException("Invalid appointment type");
}

}
