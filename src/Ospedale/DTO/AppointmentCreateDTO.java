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
    public long patientId;
    public long doctorId;
    public LocalDateTime datetime;
    public String reason;
    public boolean type; 

    public AppointmentCreateDTO() {
    }

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
