package Ospedale.View;

import Data.Storage;
import Ospedale.AppContext;
import Ospedale.Controller.NavigationController;
import Ospedale.Controller.Utils.Response;
import Ospedale.DTO.LoginDTO;
import Ospedale.Model.User.Administrator;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BaseView extends javax.swing.JFrame {

    private AppContext context;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public BaseView() {
        this(new AppContext(Storage.getInstance()));
    }

    public BaseView(AppContext context) {
        this.context = context;
        initLoginComponents();
        this.setLocationRelativeTo(null);
    }

    private void initLoginComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ospedale - Login");
        setSize(420, 260);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Ospedale Login");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Username"), gbc);

        txtUsername = new JTextField(18);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Password"), gbc);

        txtPassword = new JPasswordField(18);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(evt -> login());
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void login() {
        LoginDTO dto = new LoginDTO(txtUsername.getText(), new String(txtPassword.getPassword()));
        Response response = context.getLoginController().login(dto);

        JOptionPane.showMessageDialog(null, response.getMessage());

        if (!response.isSuccess()) {
            return;
        }

        User user = (User) response.getData().get("user");
        openViewFor(user);
    }

    private void openViewFor(User user) {
        NavigationController nav = new NavigationController(user, context);

        if (user instanceof Administrator) {
            nav.openAdminView(this);
        } else if (user instanceof Patient) {
            nav.openPatientView(this, user);
        } else if (user instanceof Doctor) {
            nav.openDoctorView(this, (Doctor) user);
        }
    }
}
