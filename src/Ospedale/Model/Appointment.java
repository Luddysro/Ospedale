/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Model;

import Ospedale.Model.User.Patient;
import Ospedale.Model.User.Doctor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class Appointment implements Serializable {
    
    private final String id;
    private Patient patient;
    private Doctor doctor;
    private Specialty specialty;
    private LocalDateTime datetime;
    private String reason;

    public void setReason(String reason) {
        this.reason = reason;
    }
    private boolean type;
    private ArrayList<Prescription> prescriptions;
    private AppointmentStatus status;
    private String diagnosis;
    private String observations;
    private String recommendedTreatment;
    private String followUp;

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setRecommendedTreatment(String recommendedTreatment) {
        this.recommendedTreatment = recommendedTreatment;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public Appointment(String id, Patient patient, Doctor doctor, Specialty specialty, LocalDateTime datetime, String reason, boolean type) {
        this.id = id;
        this.patient = patient;
        patient.addAppointment(this);
        this.doctor = doctor;
        doctor.addAppointment(this);
        this.specialty = specialty;
        this.datetime = datetime;
        this.reason = reason;
        this.type = type;
        this.status = AppointmentStatus.REQUESTED;
        this.prescriptions = new ArrayList<>();
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public boolean isType() {
        return type;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Patient getPatient() {
        return patient;
    }

    public boolean addPrescription(Prescription prescrip) {
        return this.prescriptions.add(prescrip);
    }

    @Override
    public HashMap<String, Object> serialize() {
       HashMap<String,Object> data =new HashMap<>();
       
       data.put("id",getId());
       data.put("patientId",patient.getId());
       data.put("doctorId",doctor.getId());
       data.put("specialty",specialty);
       data.put("datetime",datetime);
       data.put("reason",reason);
       data.put("type",type);
       data.put("status",status);
       data.put("diagnosis",diagnosis);
       data.put("observations",observations);
       data.put("recommendedTreatment",recommendedTreatment);
       data.put("followUp", followUp);
       
       ArrayList<HashMap<String,Object>> serializedPrescriptions = new ArrayList<>();
       for(Prescription prescription : prescriptions){
           serializedPrescriptions.add(prescription.serialize());
       }
       data.put("prescriptions",serializedPrescriptions);

       return data;
    
    }

    public String getReason() {
        return reason;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getObservations() {
        return observations;
    }

    public String getRecommendedTreatment() {
        return recommendedTreatment;
    }

    public String getFollowUp() {
        return followUp;
    }
    
    
    
    
}
