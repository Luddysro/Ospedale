/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.AppointmentCreateDTO;
import Ospedale.DTO.AppointmentTableDTO;
import Ospedale.Model.Appointment;
import Ospedale.Model.AppointmentStatus;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import Ospedale.Services.AppointmentService;
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
    
   private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    public Response createAppointment(AppointmentCreateDTO dto) {
    try {
        Appointment app = appointmentService.createAppointment(dto);
        return new Response("Appointment created: " + app.getId(), Status.CREATED);
    } catch (IllegalArgumentException e) {
        return new Response(e.getMessage(), Status.BAD_REQUEST);
    } catch (RuntimeException e) {
        return new Response(e.getMessage(), Status.NOT_FOUND);
    }
}
 public Response cancelAppointment(String idAppointment) {
    try {
        appointmentService.cancelAppointment(idAppointment);
        return new Response("Appointment canceled successfully", Status.OK);
    } catch (RuntimeException e) {
        return new Response(e.getMessage(), Status.NOT_FOUND);
    }
}

   public List<AppointmentTableDTO> getPatientAppointments(long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

   public List<AppointmentTableDTO> getDoctorAppointments(long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId, null);
    }

   public List<AppointmentTableDTO> getPendingDoctorAppointments(long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId, AppointmentStatus.PENDING);
    }

   public Response acceptAppointment(String idAppointment) {
        try {
            appointmentService.acceptAppointment(idAppointment);
            return new Response("Appointment accepted", Status.OK);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }

   public Response completeAppointment(String idAppointment, String diagnosis, String observations,
                                       String recommendedTreatment, String followUp) {
        try {
            appointmentService.completeAppointment(idAppointment, diagnosis, observations, recommendedTreatment, followUp);
            return new Response("Appointment completed", Status.OK);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }

   public Response rescheduleAppointment(String idAppointment, String time, String reason) {
        try {
            appointmentService.rescheduleAppointment(idAppointment, time, reason);
            return new Response("Appointment rescheduled", Status.OK);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }

   public Response addPrescription(String idAppointment, String medicationName, String dose,
                                   String administrationRoute, String treatmentDuration,
                                   String additionalInformation, String frequency) {
        try {
            appointmentService.addPrescription(idAppointment, medicationName, dose,
                    administrationRoute, treatmentDuration, additionalInformation, frequency);
            return new Response("Prescription added", Status.CREATED);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }
}
