/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.Appointment;
import Ospedale.Model.Hospitalization;
import Ospedale.Model.User.User;
import java.util.ArrayList;

public class Storage {

    private static Storage instance;

    private ArrayList<User> users;
    private ArrayList<Appointment> appointments;
    private ArrayList<Hospitalization> hospitalizations;

    private Storage() {

        users = new ArrayList<>();
        appointments = new ArrayList<>();
        hospitalizations = new ArrayList<>();
    }

    public static Storage getInstance() {

        if(instance == null){
            instance = new Storage();
        }

        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }
}