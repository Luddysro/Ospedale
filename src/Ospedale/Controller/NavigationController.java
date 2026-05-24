/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Ospedale.AppContext;
import Ospedale.Model.User.User;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Administrator;
import Ospedale.Model.User.Patient;
import Ospedale.View.AdminView;
import Ospedale.View.BaseView;
import Ospedale.View.DoctorView;
import Ospedale.View.PatientView;
import javax.swing.JFrame;

/**
 *
 * @author luddy
 */
public class NavigationController {

    private User user;
    private AppointmentController appctrl;
    private HospitalizationController hospctrl;
    private PatientController ptctrl;
    private DoctorController doctrl;
    private AppContext context;

    public NavigationController(User user,
                                AppointmentController appctrl,
                                HospitalizationController hospctrl,
                                PatientController ptctrl,
                                DoctorController doctrl) {
        this.user = user;
        this.appctrl = appctrl;
        this.hospctrl = hospctrl;
        this.ptctrl = ptctrl;
        this.doctrl = doctrl;
    }

    public NavigationController(User user, AppContext context) {
        this(user,
             context.getAppointmentController(),
             context.getHospitalizationController(),
             context.getPatientController(),
             context.getDoctorController());
        this.context = context;
    }

    public void openAdminView(JFrame current) {

        current.setVisible(false);

        AdminView admin = context == null ? new AdminView(user, appctrl, hospctrl, ptctrl, doctrl) : new AdminView(user, context);
        admin.setVisible(true);
    }

    public void openPatientView(JFrame current, User patient) {
        current.setVisible(false);

        PatientView patientView = context == null
                ? new PatientView(patient, this, appctrl, doctrl, hospctrl, ptctrl)
                : new PatientView(patient, context, this, appctrl, doctrl, hospctrl, ptctrl);
        patientView.setVisible(true);
    }

    public void openDoctorView(JFrame current, DoctorView doctorView) {
        current.setVisible(false);
        doctorView.setVisible(true);
    }

    public void openDoctorView(JFrame current, Doctor doctor) {
        current.setVisible(false);

        DoctorView doctorView = context == null
                ? new DoctorView(user, doctor, new java.util.ArrayList<>(), new java.util.ArrayList<>(), new java.util.ArrayList<>())
                : new DoctorView(user, doctor, context);
        doctorView.setVisible(true);
    }

    public void openLogin(JFrame current) {
        current.setVisible(false);
        BaseView login = context == null ? new BaseView() : new BaseView(context);
        login.setVisible(true);
    }

    public void openViewForUserId(JFrame current, long userId) {
        User resolvedUser = findUser(userId);
        if (resolvedUser instanceof Administrator) {
            this.user = resolvedUser;
            openAdminView(current);
        } else if (resolvedUser instanceof Patient) {
            this.user = resolvedUser;
            openPatientView(current, resolvedUser);
        } else if (resolvedUser instanceof Doctor) {
            this.user = resolvedUser;
            openDoctorView(current, (Doctor) resolvedUser);
        }
    }

    public void openDoctorViewById(JFrame current, long doctorId) {
        User resolvedDoctor = findUser(doctorId);
        if (resolvedDoctor instanceof Doctor) {
            openDoctorView(current, (Doctor) resolvedDoctor);
        }
    }

    public void openPatientViewById(JFrame current, long patientId) {
        User resolvedPatient = findUser(patientId);
        if (resolvedPatient instanceof Patient) {
            openPatientView(current, resolvedPatient);
        }
    }

    public boolean canReturnToAdmin() {
        return user instanceof Administrator;
    }

    private User findUser(long userId) {
        if (context == null) {
            return user;
        }
        for (User currentUser : context.getStorage().getUsers()) {
            if (currentUser.getId() == userId) {
                return currentUser;
            }
        }
        return null;
    }
}
