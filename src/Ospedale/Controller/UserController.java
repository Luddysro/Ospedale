/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author luddy
 */
public class UserController {
    private Storage storage;

    public UserController(Storage storage) {
        this.storage = storage;
    }
     
  public Response updatePatient(long id, String firstname, String lastname, boolean gender, 
        LocalDate birthdate, String address, long phone, String email, String username,
        String password, String confirmPassword) 
        {
    if (!password.equals(confirmPassword)) {
        return new Response("Passwords don't match", Status.BAD_REQUEST);
    }
    
    for (User user : storage.getUsers()) {
        if (user.getId() == id && user instanceof Patient) {
            Patient patient = (Patient) user;
            patient.setFirstname(firstname);
            patient.setLastname(lastname);
            patient.setGender(gender);
            patient.setBirthdate(birthdate);
            patient.setAddress(address);
            patient.setPhone(phone);
            patient.setEmail(email);
            patient.setUsername(username);
            patient.setPassword(password);

            return new Response("Patient updated successfully", Status.OK);
        }
    }

    return new Response("Patient not found", Status.NOT_FOUND);
}
  public ArrayList<Doctor> getDoctors() {

    ArrayList<Doctor> doctors =
            new ArrayList<>();

    for (User user : storage.getUsers()) {

        if (user instanceof Doctor) {
            doctors.add((Doctor) user);
        }
    }

    return doctors;
}
    }
