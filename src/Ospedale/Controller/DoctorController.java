/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.Storage;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class DoctorController {

    private Storage storage;

    public DoctorController(Storage storage) {
        this.storage = storage;
    }

    public List<Doctor> getDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        for (User user : storage.getUsers()) {
            if (user instanceof Doctor) {
                doctors.add((Doctor) user);
            }
        }

        return doctors;
    }
     public List<String> getDoctorNames() {
    List<String> names = new ArrayList<>();

    for (Doctor doc : getDoctors()) {
        names.add(doc.getFirstname() + " " + doc.getLastname());
    }

    return names;
}
}
