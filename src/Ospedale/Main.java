/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale;

import Data.AppointmentRepo;
import Data.Storage;
import Data.UserRepository;
import Ospedale.Controller.AppointmentController;
import Ospedale.Controller.DoctorController;
import Ospedale.Controller.HospitalizationController;
import Ospedale.Controller.PatientController;
import Ospedale.Services.AppointmentService;
import Ospedale.Services.PatientService;
import Ospedale.View.BaseView;

public class Main {

public static void main(String[] args) {
    Storage storage = Storage.getInstance();

    UserRepository userRepository = new UserRepository(storage);
    AppointmentRepo appointmentRepo = new AppointmentRepo(storage);

    PatientService patientService = new PatientService(userRepository);
    AppointmentService appointmentService =
    new AppointmentService(appointmentRepo, userRepository);

    PatientController patientController =
            new PatientController(patientService);

    AppointmentController appointmentController =
            new AppointmentController(appointmentService);

    DoctorController doctorController =
            new DoctorController(storage);

    HospitalizationController hospitalizationController =
            new HospitalizationController(storage);

    BaseView baseView = new BaseView();
    baseView.setVisible(true);
}
}