/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

public class PatientController {

    public Response<Patient> updatePatient(
            Patient patient,
            String firstname,
            String lastname,
            String address,
            String email
    ) {

        if(firstname.isBlank() || lastname.isBlank()){

            return new Response<>(
                    StatusCode.BAD_REQUEST,
                    "Firstname and lastname are required",
                    null
            );
        }

        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setAddress(address);
        patient.setEmail(email);

        return new Response<>(
                StatusCode.OK,
                "Patient updated successfully",
                patient
        );
    }
}