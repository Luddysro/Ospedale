/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.Model.Appointment;
import Ospedale.Model.Hospitalization;
import Ospedale.Model.RoomType;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author luddy
 */
public class HospitalizationController {
    private Storage storage;
    public HospitalizationController(Storage storage) {
    this.storage = storage;
}
     public Response createHospitalization(long patientId, long doctorId, LocalDate appointmentTime, String appointmentReason, RoomType roomtype, String observations){
        Doctor doctor = null;
        Patient patient = null;
        for(User user: storage.getUsers()){
            if (user instanceof Patient && user.getId() == patientId) {
            patient = (Patient) user;
        } else if (user instanceof Doctor && user.getId() == doctorId) {
                doctor = (Doctor) user;
            }
        }
            if (patient == null) {
        return new Response("Patient not found", Status.NOT_FOUND);
    } else if (doctor == null) {
        return new Response("Doctor not found", Status.NOT_FOUND);
    }
    String id = "HOSP-" + UUID.randomUUID();
        storage.getHospitalizations().add(new Hospitalization(id, patient, doctor, appointmentTime, appointmentReason, roomtype, observations));
        return new Response("Hospitalizaton created successfully", Status.CREATED);
}
}
