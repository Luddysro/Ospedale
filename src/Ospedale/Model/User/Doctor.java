/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Model.User;

import Ospedale.Model.Hospitalization;
import Ospedale.Model.Appointment;
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
    }

    public Doctor(long id, String username, String firstname, String lastname, String password) {
        super(id, username, firstname, lastname, password);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public ArrayList<Hospitalization> getHospitalizations() {
        return hospitalizations;
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
    public HashMap<String, Object> serialize(){

    HashMap<String, Object> data =
            super.serialize();
    data.put("hospitalizations", getHospitalizations());
    data.put("specialty", getSpecialty());
    data.put("licenseNumber",getLicenceNumber());
    data.put("assignedOffice", getAssignedOffice());
    return data;
}
}
