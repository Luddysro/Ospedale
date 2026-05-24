/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.HospitalizationRepository;
import Data.UserRepository;
import Data.AppointmentRepo;
import Ospedale.DTO.CreateHospitalizationDTO;
import Ospedale.Model.Appointment.Appointment;
import Ospedale.Model.Appointment.AppointmentStatus;
import Ospedale.Model.Hospitalization.Hospitalization;
import Ospedale.Model.Hospitalization.HospitalizationStatus;
import Ospedale.Model.RoomType;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.time.LocalDate;

/**
 *
 * @author luddy
 */
public class HospitalizationService {

    private final HospitalizationRepository hospitalizationRepo;
    private final UserRepository userRepo;
    private AppointmentRepo appointmentRepo;

    public HospitalizationService(HospitalizationRepository hospitalizationRepo, UserRepository userRepo) {
        this.hospitalizationRepo = hospitalizationRepo;
        this.userRepo = userRepo;
    }

    public HospitalizationService(HospitalizationRepository hospitalizationRepo, UserRepository userRepo, AppointmentRepo appointmentRepo) {
        this(hospitalizationRepo, userRepo);
        this.appointmentRepo = appointmentRepo;
    }

    public Hospitalization createHospitalization(CreateHospitalizationDTO dto) {
        Patient patient = userRepo.findPatientById(parseId(dto.getPatientId(), "Invalid patient"));
        Doctor doctor = userRepo.findDoctorById(parseId(dto.getDoctorId(), "Invalid doctor"));

        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }

        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }

        Hospitalization hospitalization = new Hospitalization(
                nextHospitalizationId(patient.getId()),
                patient,
                doctor,
                parseDate(dto.getEstimatedAdmission()),
                dto.getReason(),
                parseRoomType(dto.getRoomType()),
                dto.getObservations()
        );

        hospitalizationRepo.save(hospitalization);
        return hospitalization;
    }

    public void approveHospitalization(String id) {
        Hospitalization hospitalization = findHospitalizationOrThrow(id);
        if (hospitalization.getStatus() != HospitalizationStatus.REQUESTED) {
            throw new IllegalArgumentException("Only requested hospitalizations can be approved");
        }
        hospitalization.setStatus(HospitalizationStatus.ONGOING);
        hospitalizationRepo.update(hospitalization);
    }

    public void cancelHospitalization(String id) {
        Hospitalization hospitalization = findHospitalizationOrThrow(id);
        if (hospitalization.getStatus() != HospitalizationStatus.REQUESTED) {
            throw new IllegalArgumentException("Only requested hospitalizations can be denied");
        }

        hospitalization.setStatus(HospitalizationStatus.CANCELED);
        hospitalizationRepo.update(hospitalization);
    }

    public Hospitalization createFromAppointment(String appointmentId, String estimatedAdmission,
                                                 String reason, String roomType, String observations) {
        if (appointmentRepo == null) {
            throw new RuntimeException("Appointment repository not available");
        }
        Appointment appointment = appointmentRepo.findById(appointmentId);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
        if (appointment.getStatus() != AppointmentStatus.PENDING) {
            throw new IllegalArgumentException("Hospitalizations from appointments require a pending appointment");
        }
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepo.update(appointment);

        Hospitalization hospitalization = new Hospitalization(
                nextHospitalizationId(appointment.getPatient().getId()),
                appointment.getPatient(),
                appointment.getDoctor(),
                parseDate(estimatedAdmission),
                reason,
                parseRoomType(roomType),
                observations,
                HospitalizationStatus.ONGOING
        );
        hospitalizationRepo.save(hospitalization);
        return hospitalization;
    }

    private long parseId(String value, String message) {
        try {
            return Long.parseLong(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private LocalDate parseDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid hospitalization date");
        }
    }

    private RoomType parseRoomType(String value) {
        try {
            return RoomType.valueOf(value);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Invalid room type");
        }
    }

    private Hospitalization findHospitalizationOrThrow(String id) {
        Hospitalization hospitalization = hospitalizationRepo.findById(id);
        if (hospitalization == null) {
            throw new RuntimeException("Hospitalization not found");
        }
        return hospitalization;
    }

    private String nextHospitalizationId(long patientId) {
        int max = -1;
        String prefix = "H-" + patientId + "-";
        for (Hospitalization hospitalization : hospitalizationRepo.findByPatient(patientId)) {
            if (hospitalization.getId().startsWith(prefix)) {
                try {
                    max = Math.max(max, Integer.parseInt(hospitalization.getId().substring(prefix.length())));
                } catch (RuntimeException e) {
                    max = max;
                }
            }
        }
        return prefix + String.format("%04d", max + 1);
    }
}
