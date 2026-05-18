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
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<Object[]> getPatientAppointments(long patientId) {

    Patient patient = null;

    for (User u : storage.getUsers()) {
        if (u instanceof Patient && u.getId() == patientId) {
            patient = (Patient) u;
            break;
        }
    }

    if (patient == null) return new ArrayList<>();

    List<Object[]> rows = new ArrayList<>();

    for (Appointment a : patient.getAppointments()) {
        rows.add(new Object[]{
            a.getId(),
            a.getDatetime().toString(),
            a.getDoctor().getFirstname() + " " + a.getDoctor().getLastname(),
            a.getSpecialty().name(),
            a.isType() ? "In-person" : "Remote",
            a.getStatus().name()
        });
    }

    return rows;
}
}
