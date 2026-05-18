/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.Hospitalization;

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
}
