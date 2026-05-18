/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.DTO;

import Ospedale.Model.RoomType;
import java.time.LocalDate;

/**
 *
 * @author luddy
 */
public class CreateHospitalizationDTO {
    long patientId;
    long doctorId;
    LocalDate estimatedAdmission;
    String reason;
    RoomType roomType;
    String observations;
}
