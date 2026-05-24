/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

/**
 *
 * @author luddy
 */
public class CreateHospitalizationDTO {
    private String patientId;
    private String doctorId;
    private String estimatedAdmission;
    private String reason;
    private String roomType;
    private String observations;

    public CreateHospitalizationDTO(long patientId,
                                    String doctorId,
                                    String estimatedAdmission,
                                    String reason,
                                    String roomType,
                                    String observations) {
        this(String.valueOf(patientId), doctorId, estimatedAdmission, reason, roomType, observations);
    }

    public CreateHospitalizationDTO(String patientId,
                                    String doctorId,
                                    String estimatedAdmission,
                                    String reason,
                                    String roomType,
                                    String observations) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.estimatedAdmission = estimatedAdmission;
        this.reason = reason;
        this.roomType = roomType;
        this.observations = observations;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getEstimatedAdmission() {
        return estimatedAdmission;
    }

    public String getReason() {
        return reason;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getObservations() {
        return observations;
    }
}
