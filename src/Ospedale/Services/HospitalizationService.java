/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.HospitalizationRepository;
import Data.UserRepository;
import Ospedale.DTO.CreateHospitalizationDTO;
import Ospedale.Model.Hospitalization;
import Ospedale.Model.HospitalizationStatus;
import Ospedale.Model.RoomType;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author luddy
 */
public class HospitalizationService {

    private final HospitalizationRepository hospitalizationRepo;
    private final UserRepository userRepo;

    public HospitalizationService(HospitalizationRepository hospitalizationRepo, UserRepository userRepo) {
        this.hospitalizationRepo = hospitalizationRepo;
        this.userRepo = userRepo;
    }

    public Hospitalization createHospitalization(CreateHospitalizationDTO dto) {
        Patient patient = userRepo.findPatientById(dto.getPatientId());
        Doctor doctor = userRepo.findDoctorById(parseId(dto.getDoctorId(), "Invalid doctor"));

        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }

        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }

        Hospitalization hospitalization = new Hospitalization(
                "HOSP-" + UUID.randomUUID(),
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

    public void cancelHospitalization(String id) {
        Hospitalization hospitalization = hospitalizationRepo.findById(id);

        if (hospitalization == null) {
            throw new RuntimeException("Hospitalization not found");
        }

        hospitalization.setStatus(HospitalizationStatus.CANCELED);
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
}
