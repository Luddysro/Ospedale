/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.Appointment.Appointment;
import Ospedale.Model.Hospitalization.Hospitalization;
import Ospedale.Model.User.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static Storage instance;

    private ArrayList<User> users;
    private ArrayList<Appointment> appointments;
    private ArrayList<Hospitalization> hospitalizations;
    private ArrayList<ModelChangeListener> listeners;

    private Storage() {

        users = new ArrayList<>();
        appointments = new ArrayList<>();
        hospitalizations = new ArrayList<>();
        listeners = new ArrayList<>();
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

    public void addModelChangeListener(ModelChangeListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void notifyModelChanged(String modelName) {
        List<ModelChangeListener> snapshot = new ArrayList<>(listeners);
        for (ModelChangeListener listener : snapshot) {
            listener.onModelChanged(modelName);
        }
    }
}
