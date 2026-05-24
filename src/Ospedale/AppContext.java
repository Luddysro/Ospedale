package Ospedale;

import Data.AppointmentRepo;
import Data.HospitalizationRepository;
import Data.Storage;
import Data.UserRepository;
import Ospedale.Controller.AppointmentController;
import Ospedale.Controller.DoctorController;
import Ospedale.Controller.HospitalizationController;
import Ospedale.Controller.LoginController;
import Ospedale.Controller.PatientController;
import Ospedale.Services.AppointmentService;
import Ospedale.Services.DoctorService;
import Ospedale.Services.HospitalizationService;
import Ospedale.Services.LoginService;
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
        HospitalizationRepository hospitalizationRepository = new HospitalizationRepository(storage);
        AppointmentService appointmentService = new AppointmentService(appointmentRepo, userRepository);
        HospitalizationService hospitalizationService = new HospitalizationService(hospitalizationRepository, userRepository, appointmentRepo);
        PatientService patientService = new PatientService(userRepository);
        DoctorService doctorService = new DoctorService(userRepository);
        LoginService loginService = new LoginService(userRepository);
        this.loginController = new LoginController(loginService);
        this.appointmentController = new AppointmentController(appointmentService);
        this.hospitalizationController = new HospitalizationController(hospitalizationService);
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
