/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.AppointmentRepo;
import Data.UserRepository;
import Ospedale.DTO.AppointmentTableDTO;
import Ospedale.DTO.AppointmentCreateDTO;
import Ospedale.Model.Appointment.Appointment;
import Ospedale.Model.Appointment.AppointmentStatus;
import Ospedale.Model.Prescription;
import Ospedale.Model.Specialty;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    LocalDateTime datetime = parseDateTime(dto.getDate(), dto.getTime());
    Doctor doctor = resolveAvailableDoctor(dto.getDoctorId(), datetime);

    if (patient == null) {
        throw new RuntimeException("Patient not found");
    }

    if (doctor == null) {
        throw new RuntimeException("Doctor not found");
    }

    String id = nextAppointmentId(patient.getId());

    Appointment app = new Appointment(
        id,
        patient,
        doctor,
        doctor.getSpecialty(),
        datetime,
        dto.getReason(),
        parseType(dto.getType())
    );

    appointmentRepo.save(app);
    

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
    if (ap.getStatus() == AppointmentStatus.COMPLETED)
        throw new IllegalArgumentException("Completed appointments cannot be canceled");

    ap.setStatus(AppointmentStatus.CANCELED);
    appointmentRepo.update(ap);
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
    if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
        throw new IllegalArgumentException("Only requested appointments can be accepted");
    }
    appointment.setStatus(AppointmentStatus.PENDING);
    appointmentRepo.update(appointment);
}

public void completeAppointment(String idAppointment, String diagnosis, String observations,
                                String recommendedTreatment, String followUp) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    if (appointment.getStatus() != AppointmentStatus.PENDING) {
        throw new IllegalArgumentException("Only pending appointments can be completed");
    }
    appointment.setStatus(AppointmentStatus.COMPLETED);
    appointment.setDiagnosis(diagnosis);
    appointment.setObservations(observations);
    appointment.setRecommendedTreatment(recommendedTreatment);
    appointment.setFollowUp(followUp);
    appointmentRepo.update(appointment);
}

public void rescheduleAppointment(String idAppointment, String time, String reason) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    if (appointment.getStatus() == AppointmentStatus.COMPLETED
            || appointment.getStatus() == AppointmentStatus.CANCELED) {
        throw new IllegalArgumentException("Completed or canceled appointments cannot be rescheduled");
    }
    LocalTime newTime = parseAppointmentTime(time);
    LocalDateTime newDatetime = LocalDateTime.of(appointment.getDatetime().toLocalDate(), newTime);
    if (!isDoctorAvailable(appointment.getDoctor(), newDatetime, appointment.getId())) {
        throw new IllegalArgumentException("Doctor is not available at that time");
    }
    try {
        appointment.setDatetime(newDatetime);
    } catch (RuntimeException e) {
        throw new IllegalArgumentException("Invalid appointment time");
    }
    appointment.setReason(appendRescheduleReason(appointment.getReason(), reason));
    appointmentRepo.update(appointment);
}

public Prescription addPrescription(String idAppointment, String medicationName, String dose,
                                    String administrationRoute, String treatmentDuration,
                                    String additionalInformation, String frequency) {
    Appointment appointment = findAppointmentOrThrow(idAppointment);
    if (appointment.getStatus() != AppointmentStatus.PENDING) {
        throw new IllegalArgumentException("Prescriptions require a pending appointment");
    }
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
    appointmentRepo.update(appointment);
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

private Doctor resolveAvailableDoctor(String value, LocalDateTime datetime) {
    try {
        long doctorId = parseId(value, "Invalid doctor");
        Doctor doctor = userRepo.findDoctorById(doctorId);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        if (!isDoctorAvailable(doctor, datetime, null)) {
            throw new IllegalArgumentException("Doctor is not available at that time");
        }
        return doctor;
    } catch (IllegalArgumentException e) {
        if (value != null && value.matches("\\d+")) {
            throw e;
        }
        Specialty specialty = parseSpecialty(value);
        Doctor doctor = findAvailableDoctorBySpecialty(specialty, datetime);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found for specialty or time");
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
        return LocalDateTime.of(LocalDate.parse(date), parseAppointmentTime(time));
    } catch (RuntimeException e) {
        throw new IllegalArgumentException("Invalid appointment date or time");
    }
}

private LocalTime parseAppointmentTime(String time) {
    LocalTime parsed = LocalTime.parse(time);
    int minute = parsed.getMinute();
    if (!(minute == 0 || minute == 15 || minute == 30 || minute == 45)) {
        throw new IllegalArgumentException("Invalid appointment time");
    }
    return parsed;
}

private boolean isDoctorAvailable(Doctor doctor, LocalDateTime datetime, String ignoredAppointmentId) {
    for (Appointment appointment : appointmentRepo.findByDoctorId(doctor.getId())) {
        if (ignoredAppointmentId != null && ignoredAppointmentId.equals(appointment.getId())) {
            continue;
        }
        if (appointment.getStatus() != AppointmentStatus.CANCELED
                && appointment.getDatetime().equals(datetime)) {
            return false;
        }
    }
    return true;
}

private Doctor findAvailableDoctorBySpecialty(Specialty specialty, LocalDateTime datetime) {
    for (Doctor doctor : userRepo.findDoctors()) {
        if (doctor.getSpecialty().equals(specialty) && isDoctorAvailable(doctor, datetime, null)) {
            return doctor;
        }
    }
    return null;
}

private String nextAppointmentId(long patientId) {
    int max = -1;
    String prefix = "A-" + patientId + "-";
    for (Appointment appointment : appointmentRepo.findByPatientId(patientId)) {
        if (appointment.getId().startsWith(prefix)) {
            try {
                max = Math.max(max, Integer.parseInt(appointment.getId().substring(prefix.length())));
            } catch (RuntimeException e) {
                max = max;
            }
        }
    }
    return prefix + String.format("%04d", max + 1);
}

private String appendRescheduleReason(String original, String reason) {
    if (reason == null || reason.trim().isEmpty()) {
        return original;
    }
    return original + " | Reschedule reason: " + reason;
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
