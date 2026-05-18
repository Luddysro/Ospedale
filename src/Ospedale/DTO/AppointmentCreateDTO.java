/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

/**
 *
 * @author luddy
 */
public class AppointmentCreateDTO {
    private long patientId;
    private String doctorId;
    private String date;
    private String time;
    private String reason;
    private String type;

    public AppointmentCreateDTO(long patientId,
                                String doctorId,
                                String date,
                                String time,
                                String reason,
                                String type) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.type = type;
    }

    public long getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public String getType() {
        return type;
    }
}
