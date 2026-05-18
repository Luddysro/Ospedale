/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;

/**
 *
 * @author luddy
 */
public class UserRepository {

    private final Storage storage;

    public UserRepository(Storage storage) {
        this.storage = storage;
    }

    public Patient findPatientById(long id) {
        for (User u : storage.getUsers()) {
            if (u instanceof Patient && u.getId() == id) {
                return (Patient) u;
            }
        }
        return null;
    }

    public Doctor findDoctorById(long id) {
        for (User u : storage.getUsers()) {
            if (u instanceof Doctor && u.getId() == id) {
                return (Doctor) u;
            }
        }
        return null;
    }
}