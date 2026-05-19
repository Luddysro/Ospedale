/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.PatientUpdateDTO;
import Ospedale.Services.PatientService;

public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    public Response updatePatient(PatientUpdateDTO dto) {
        try {
            patientService.updatePatient(dto);
            return new Response("Patient updated", Status.OK);
        } catch (IllegalArgumentException e) {
            return new Response("Passwords don't match", Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }
}
