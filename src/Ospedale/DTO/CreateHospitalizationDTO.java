/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

import Ospedale.Model.RoomType;
import java.time.LocalDate;

/**
 *
 * @author luddy
 */
public class CreateHospitalizationDTO {
    private long patientId;
    private long doctorId;
    private LocalDate estimatedAdmission;
    private String reason;
    private RoomType roomType;
    private String observations;

    public long getPatientId() {
        return patientId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public LocalDate getEstimatedAdmission() {
        return estimatedAdmission;
    }

    public String getReason() {
        return reason;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getObservations() {
        return observations;
    }
    
}
