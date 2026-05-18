/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Ospedale.Model.Appointment;
import Ospedale.Model.Hospitalization;
import Ospedale.Model.User.User;
import Ospedale.View.AdminView;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author luddy
 */
public class NavigationController {

    private User user;

    public NavigationController(User user) {
        this.user = user;
    }

    public void openAdminView(JFrame current) {

        current.setVisible(false);

        AdminView admin = new AdminView(user);
        admin.setVisible(true);
    }
}
