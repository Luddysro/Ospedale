package Ospedale.Model;

import Ospedale.Model.Appointment.Appointment;
import java.util.HashMap;

/**
 *
 * @author jjlora
 */
public class Prescription implements Serializable {
    private Appointment appointment;
    private String medicationName;
    private double dose;
    private String administrationRoute;
    private int treatmentDuration;
    private String additionalInstructions;
    private int frecuency;

    public Prescription(Appointment appointment, String medicationName, double dose, String administrationRoute, int treatmentDuration, String additionalInstructions, int frecuency) {
        this.appointment = appointment;
       
        this.medicationName = medicationName;
        this.dose = dose;
        this.administrationRoute = administrationRoute;
        this.treatmentDuration = treatmentDuration;
        this.additionalInstructions = additionalInstructions;
        this.frecuency = frecuency;
    }

    @Override
    public HashMap<String, Object> serialize() {
     HashMap<String,Object> data = new HashMap<>();
     
     data.put("appointmentId",appointment.getId());
     data.put("medicationName",medicationName);        
     data.put("dose",dose);        
     data.put("administrationRoute",administrationRoute);        
     data.put("treatmentDuration",treatmentDuration);        
     data.put("additionalInstructions",additionalInstructions);        
     data.put("frequency",frecuency);  
     
     return data;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public double getDose() {
        return dose;
    }

    public String getAdministrationRoute() {
        return administrationRoute;
    }

    public int getTreatmentDuration() {
        return treatmentDuration;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public int getFrecuency() {
        return frecuency;
    }
    
    
    
}
