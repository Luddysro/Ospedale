package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.LoginDTO;
import Ospedale.Model.User.User;
import java.util.HashMap;

public class LoginController {

    private Storage storage;

    public LoginController(Storage storage) {
        this.storage = storage;
    }

    public Response login(LoginDTO dto) {
        for (User user : storage.getUsers()) {
            if (user.getUsername().equals(dto.getUsername()) && user.getPassword().equals(dto.getPassword())) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("user", user);
                return new Response("Login successful", Status.OK, data);
            }
        }

        return new Response("Invalid username or password", Status.NOT_FOUND);
    }
}
