/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.Specialty;
import Ospedale.Model.User.Administrator;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class JSONLoader {

    public static void loadUsers() {

        Storage storage = Storage.getInstance();

        try {
            String content = new String(Files.readAllBytes(Paths.get("json/users.json")));

            JSONObject root = new JSONObject(content);
            JSONArray usersArray = root.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {

                JSONObject json = usersArray.getJSONObject(i);
                String type = json.getString("type");
                long id = json.getLong("id");
                String username = json.getString("username");
                String firstname = json.getString("firstname");
                String lastname = json.getString("lastname");
                String password = json.getString("password");
                String email = json.getString("email");
                LocalDate birthdate = LocalDate.parse(json.getString("birthdate"));
                boolean gender = json.getBoolean("gender");
                long phone = json.getLong("phone");
                String address = json.getString("address");
                Specialty specialty = Specialty.valueOf(json.getString("specialty"));
                String licenceNumber = json.getString("licenceNumber");
                String assignedOffice = json.getString("assignedOffice");
                if (type.equals("patient")) {
                    Patient patient = new Patient(id,username,firstname,lastname,password,email,birthdate,gender,phone,address);
                    storage.getUsers().add(patient);
                } else if (type.equals("admin")) {
                    Administrator admin = new Administrator(id, username, firstname,lastname , password);
                    storage.getUsers().add(admin);
                } else if (type.equals("doctor")) {
                    Doctor doctor = new Doctor(id,username,firstname,lastname,password,specialty,licenceNumber,assignedOffice);
                    storage.getUsers().add(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
