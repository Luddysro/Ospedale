package Ospedale;

import Data.AppointmentRepo;
import Data.Storage;
import Data.UserRepository;
import Ospedale.Controller.AppointmentController;
import Ospedale.Controller.DoctorController;
import Ospedale.Controller.HospitalizationController;
import Ospedale.Controller.LoginController;
import Ospedale.Controller.PatientController;
import Ospedale.Services.AppointmentService;
import Ospedale.Services.DoctorService;
import Ospedale.Services.PatientService;

public class AppContext {

    private Storage storage;
    private LoginController loginController;
    private AppointmentController appointmentController;
    private HospitalizationController hospitalizationController;
    private PatientController patientController;
    private DoctorController doctorController;

    public AppContext(Storage storage) {
        this.storage = storage;

        UserRepository userRepository = new UserRepository(storage);
        AppointmentRepo appointmentRepo = new AppointmentRepo(storage);
        AppointmentService appointmentService = new AppointmentService(appointmentRepo, userRepository);
        PatientService patientService = new PatientService(userRepository);
        DoctorService doctorService = new DoctorService(userRepository);

        this.loginController = new LoginController(storage);
        this.appointmentController = new AppointmentController(appointmentService);
        this.hospitalizationController = new HospitalizationController(storage);
        this.patientController = new PatientController(patientService);
        this.doctorController = new DoctorController(doctorService);
    }

    public Storage getStorage() {
        return storage;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public AppointmentController getAppointmentController() {
        return appointmentController;
    }

    public HospitalizationController getHospitalizationController() {
        return hospitalizationController;
    }

    public PatientController getPatientController() {
        return patientController;
    }

    public DoctorController getDoctorController() {
        return doctorController;
    }
}
