/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Ospedale.Model.User.User;
import Ospedale.View.AdminView;
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

    public void openAdminView(JFrame current) {

        current.setVisible(false);

        AdminView admin = new AdminView(user, appctrl, hospctrl, ptctrl, doctrl);
        admin.setVisible(true);
    }
}
