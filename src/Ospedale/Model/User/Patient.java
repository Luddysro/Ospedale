/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Model.User;

import Ospedale.Model.Appointment.Appointment;
import Ospedale.Model.Hospitalization.Hospitalization;
import Ospedale.Model.User.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class Patient extends User {
    
    private String email;
    private LocalDate birthdate;
    private boolean gender;
    private long phone;
    private String address;
    private ArrayList<Appointment> appointments;
    
    private ArrayList<Hospitalization> hospitalizations;

       public Patient(long id, String username, String firstname, String lastname, String password, String email, LocalDate birthdate, boolean gender, long phone, String address) {
        super(id, username, firstname, lastname, password);
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.appointments = new ArrayList<>();
        this.hospitalizations = new ArrayList<>();
    }

    public Patient(long id, String username, String firstname, String lastname, String password) {
        super(id, username, firstname, lastname, password);
        this.appointments = new ArrayList<>();
        this.hospitalizations = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public boolean isGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    
    public ArrayList<Hospitalization> getHospitalizations() {
        return new ArrayList<>(hospitalizations);
    }
       
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean addHospitalization(Hospitalization hospitalization) {
        return this.hospitalizations.add(hospitalization);
    }

    public ArrayList<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
    
    public void addAppointment(Appointment a) {
        this.appointments.add(a);
    }

    
@Override
public HashMap<String, Object> serialize(){

    HashMap<String, Object> data =
            super.serialize();

    data.put("email", getEmail());
    data.put("birthdate", getBirthdate());
    data.put("gender", isGender());
    data.put("phone", getPhone());
    data.put("address", getAddress());
    ArrayList<HashMap<String,Object>> serializedAppointments = new ArrayList<>();
    for(Appointment appointment : appointments){
        serializedAppointments.add(appointment.serialize());
    }
    data.put("appointments", serializedAppointments);
    
    ArrayList<HashMap<String,Object>> serializedHospitalizations = new ArrayList<>();
    for(Hospitalization hospitalization : hospitalizations){
        serializedHospitalizations.add(hospitalization.serialize());
    }
    data.put("hospitalizations", serializedHospitalizations);

    return data;
}

}
