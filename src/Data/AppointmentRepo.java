/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;
import Ospedale.DTO.AppointmentTableDTO;
import Ospedale.Model.Appointment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class AppointmentRepo {

    private final Storage storage;

    public AppointmentRepo(Storage storage) {
        this.storage = storage;
    }

    public void save(Appointment a) {
        storage.getAppointments().add(a);
    }

public List<Appointment> findByPatientId(long id) {
    List<Appointment> list = new ArrayList<>();

    for (Appointment a : storage.getAppointments()) {
        if (a.getPatient().getId() == id) {
            list.add(a);
        }
    }

    return list;
}
public List<Appointment> findAll() {
    return new ArrayList<>(storage.getAppointments());
}
public Appointment findById(String id) {
    for (Appointment a : storage.getAppointments()) {
        if (a.getId().equals(id)) {
            return a;
        }
    }
    return null;
}
}
