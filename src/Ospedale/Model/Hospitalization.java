/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Model;

import Ospedale.Model.RoomType;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.Doctor;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class Hospitalization implements Serializable {
    
    private final String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;

    public String getId() {
        return id;
    }
    private String reason;
    private RoomType roomType;
    private String observations;
    private HospitalizationStatus status;

    public void setStatus(HospitalizationStatus status) {
        this.status = status;
    }

    public Hospitalization(String id, Patient patient, Doctor doctor, LocalDate date, String reason, RoomType roomType, String observations) {
        this.id = id;
        this.patient = patient;
        patient.addHospitalization(this);
        this.doctor = doctor;
        doctor.addHospitalization(this);
        this.date = date;
        this.reason = reason;
        this.roomType = roomType;
        this.observations = observations;
        this.status = HospitalizationStatus.REQUESTED;
    }
    public Hospitalization(String id, Patient patient, Doctor doctor, LocalDate date, String reason, RoomType roomType, String observations, HospitalizationStatus hopsS) {
        this.id = id;
        this.patient = patient;
        patient.addHospitalization(this);
        this.doctor = doctor;
        doctor.addHospitalization(this);
        this.date = date;
        this.reason = reason;
        this.roomType = roomType;
        this.observations = observations;
        this.status = hopsS;
    }

    @Override
    public HashMap<String, Object> serialize() {
        HashMap<String,Object> data =new HashMap<>();
        
        
        data.put("id", getId());
        data.put("patientId", patient.getId());
        data.put("doctorId", doctor.getId());
        data.put("date", date);
        data.put("reason", reason);
        data.put("roomType", roomType);
        data.put("observations", observations);
        data.put("status", status);

       return data;
        
        
    
    }
    
    
    
    
    
}
