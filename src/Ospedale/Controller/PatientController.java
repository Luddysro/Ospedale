/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import java.time.LocalDate;

public class PatientController {

    private Storage storage;

    public PatientController(Storage storage) {
        this.storage = storage;
    }

    public Response updatePatient(long id,
                                 String firstname,
                                 String lastname,
                                 boolean gender,
                                 LocalDate birthdate,
                                 String address,
                                 long phone,
                                 String email,
                                 String username,
                                 String password,
                                 String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return new Response("Passwords don't match", Status.BAD_REQUEST);
        }

        for (User user : storage.getUsers()) {
            if (user instanceof Patient && user.getId() == id) {

                Patient p = (Patient) user;

                p.setFirstname(firstname);
                p.setLastname(lastname);
                p.setGender(gender);
                p.setBirthdate(birthdate);
                p.setAddress(address);
                p.setPhone(phone);
                p.setEmail(email);
                p.setUsername(username);
                p.setPassword(password);

                return new Response("Patient updated", Status.OK);
            }
        }

        return new Response("Patient not found", Status.NOT_FOUND);
    }
}