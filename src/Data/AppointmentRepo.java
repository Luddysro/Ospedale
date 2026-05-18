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

    public List<AppointmentTableDTO> findByPatientId(long id) {

        List<AppointmentTableDTO> list = new ArrayList<>();

        for (Appointment a : storage.getAppointments()) {

            if (a.getPatient().getId() == id) {

                AppointmentTableDTO dto = new AppointmentTableDTO(
                    a.getId(),
                    a.getDatetime().toString(),
                    a.getDoctor().getFirstname() + " " + a.getDoctor().getLastname(),
                    a.getSpecialty().name(),
                    a.isType() ? "In-person" : "Remote",
                    a.getStatus().name()
                );

                list.add(dto);
            }
        }

        return list;
    }
}