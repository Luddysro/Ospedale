/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Ospedale.Model.User.Administrator;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONLoader {

    public static void loadUsers() {

        Storage storage = Storage.getInstance();

        try {
            String content
                    = new String(Files.readAllBytes(Paths.get("users.json")));

            JSONArray usersArray
                    = new JSONArray(content);

            for (int i = 0; i < usersArray.length(); i++) {

                JSONObject json = usersArray.getJSONObject(i);
                String type = json.getString("type");
                long id = json.getLong("id");
                String username = json.getString("username");
                String firstname = json.getString("firstname");
                String lastname = json.getString("lastname");
                String password = json.getString("password");
                if (type.equals("patient")) {
                    Patient patient = new Patient(id, firstname, lastname, username, password);
                    storage.getUsers().add(patient);
                } else if (type.equals("admin")) {
                    Administrator admin = new Administrator(id, firstname, lastname, username, password);
                    storage.getUsers().add(admin);
                } else if (type.equals("doctor")) {
                    Doctor doctor = new Doctor(id, firstname, lastname, username, password);
                    storage.getUsers().add(doctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
