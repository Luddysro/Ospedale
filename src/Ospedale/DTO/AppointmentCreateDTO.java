/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

import Ospedale.Model.Appointment;
import java.time.LocalDateTime;

/**
 *
 * @author luddy
 */
public class AppointmentCreateDTO {
    private long patientId;
    private long doctorId;
    private LocalDateTime datetime;
    private String reason;
    private boolean type; 

    public long getPatientId() {
        return patientId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getReason() {
        return reason;
    }

    public boolean isType() {
        return type;
    }
}