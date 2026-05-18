/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.Model.Appointment;
import Ospedale.Model.AppointmentStatus;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.User;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author luddy
 */
public class AppointmentController {
    private Storage storage;

    public AppointmentController(Storage storage) {
        this.storage = storage;
    }
    
    public Response cancelAppointment(String idAppointment){
        
    for(Appointment ap: storage.getAppointments()){
            if (ap.getId().equals(idAppointment)) {
                ap.setStatus(AppointmentStatus.CANCELED);
                return new Response("Appointment canceled successfully",Status.OK);
            }
        }
    return new Response("Appointment not found", Status.NOT_FOUND);
    }
    public Response createAppointment(long patientId, long doctorId, LocalDate appointmentDate, LocalTime appointTime, String appointDate, boolean appointmentType){
        Doctor doctor = null;
        for(User use: storage.getUsers()){
            if (use.getId() == doctorId) {
                doctor = (Doctor) use;
            }
        }
        storage.getAppointments().add(new Appointment(appointDate, patient, doctor, doctor.getSpecialty(), Finally, appointDate, appointmentType);
        return new Response("Appointment created successfully", Status.OK);
}
}
