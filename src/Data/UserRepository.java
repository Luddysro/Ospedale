/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import Ospedale.Model.Specialty;
import java.util.ArrayList;
import java.util.List;

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

    public Doctor findDoctorBySpecialty(Specialty specialty) {
        for (User u : storage.getUsers()) {
            if (u instanceof Doctor && ((Doctor) u).getSpecialty().equals(specialty)) {
                return (Doctor) u;
            }
        }
        return null;
    }

    public void save(User user) {
        storage.getUsers().add(user);
    }

    public User findById(long id) {
        for (User u : storage.getUsers()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<Doctor> findDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        for (User u : storage.getUsers()) {
            if (u instanceof Doctor) {
                doctors.add((Doctor) u);
            }
        }

        return doctors;
    }

    public List<Patient> findPatients() {
        List<Patient> patients = new ArrayList<>();

        for (User u : storage.getUsers()) {
            if (u instanceof Patient) {
                patients.add((Patient) u);
            }
        }

        return patients;
    }
    
    public User findUsername(String username){
        for (User user :storage.getUsers()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
    
    public boolean existsById(long id) {
        return findById(id) != null;
    }
    
    public boolean existsByUsername(String username) {
        return findUsername(username) != null;
    }
    
    public boolean existsUsernameForAnotherUser(String username, long currentUserId) {
        for (User user : storage.getUsers()) {
            if (user.getUsername().equals(username) && user.getId() != currentUserId) {
                return true;
            }
        }
        
        return false;
    }
}
