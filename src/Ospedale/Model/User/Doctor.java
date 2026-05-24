/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Model.User;

import Ospedale.Model.Hospitalization.Hospitalization;
import Ospedale.Model.Appointment.Appointment;
import Ospedale.Model.User.User;
import Ospedale.Model.Specialty;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class Doctor extends User {
    
    private Specialty specialty;
    private String licenceNumber;
    private String assignedOffice;
    private ArrayList<Appointment> appointments;
    private ArrayList<Hospitalization> hospitalizations;

    public Doctor(long id, String username, String firstname, String lastname, String password, Specialty specialty, String licenceNumber, String assignedOffice) {
        super(id, username, firstname, lastname, password);
        hospitalizations = new ArrayList<>();
        this.specialty = specialty;
        this.licenceNumber = licenceNumber;
        this.assignedOffice = assignedOffice;
        this.appointments = new ArrayList<>();
    }

    

    public ArrayList<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public ArrayList<Hospitalization> getHospitalizations() {
        return new ArrayList<>(hospitalizations);
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }
    
    
    public boolean addHospitalization(Hospitalization hosp){
        return hospitalizations.add(hosp);
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setAssignedOffice(String assignedOffice) {
        this.assignedOffice = assignedOffice;
    }
    
    public void addAppointment(Appointment a) {
        this.appointments.add(a);
    }
    public HashMap<String, Object> serialize(){

    HashMap<String, Object> data =
            super.serialize();
    ArrayList<HashMap<String,Object>> serializedHospitalizations = new ArrayList<>();
    for (Hospitalization hospitalization : hospitalizations){
        serializedHospitalizations.add(hospitalization.serialize());
    }
    data.put("hospitalizations", serializedHospitalizations);
    
    ArrayList<HashMap<String, Object>> serializedAppointments = new ArrayList<>();
    
    for (Appointment appointment : appointments) {
        serializedAppointments.add(appointment.serialize());
    }
    
    data.put("appointments", serializedAppointments);
    data.put("specialty", getSpecialty());
    data.put("licenseNumber",getLicenceNumber());
    data.put("assignedOffice", getAssignedOffice());
    return data;
}
}
