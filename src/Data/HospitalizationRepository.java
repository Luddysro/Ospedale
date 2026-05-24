/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.Hospitalization;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luddy
 */
public class HospitalizationRepository {

    private final Storage storage;

    public HospitalizationRepository(Storage storage) {
        this.storage = storage;
    }

    public void save(Hospitalization hospitalization) {
        storage.getHospitalizations().add(hospitalization);
    }

    public Hospitalization findById(String id) {
        for (Hospitalization hospitalization : storage.getHospitalizations()) {
            if (hospitalization.getId().equals(id)) {
                return hospitalization;
            }
        }
        return null;
    }

    public List<Hospitalization> findByDoctorId(long id) {
        List<Hospitalization> hospitalizations = new ArrayList<>();

        for (Hospitalization hospitalization : storage.getHospitalizations()) {
            if (hospitalization.getDoctor().getId() == id) {
                hospitalizations.add(hospitalization);
            }
        }

        return hospitalizations;
    }
    
    public List<Hospitalization> findByPatient(long id){
        List<Hospitalization> hospitalizations = new ArrayList<>();
        
        for (Hospitalization hospitalization : storage.getHospitalizations()){
            if (hospitalization.getPatient().getId()== id){
            hospitalizations.add(hospitalization);
            }
            
           
        }
         return hospitalizations;
    }
}
