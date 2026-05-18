/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

/**
 *
 * @author luddy
 */
public class AppointmentTableDTO {

    private String id;
    private String datetime;
    private String doctorName;
    private String specialty;
    private String type;
    private String status;

    public AppointmentTableDTO(String id, String datetime, String doctorName,
                               String specialty, String type, String status) {
        this.id = id;
        this.datetime = datetime;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.type = type;
        this.status = status;
    }

    public String getId() { 
        return id; 
    }
    public String getDatetime() {
        return datetime; 
    }
    public String getDoctorName() { 
        return doctorName; 
    }
    public String getSpecialty() { 
        return specialty; 
    }
    public String getType() {
        return type; 
    }
    public String getStatus() {
        return status; 
    }
}