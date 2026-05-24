package Ospedale.Controller;

import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.LoginDTO;
import Ospedale.Model.User.Administrator;
import Ospedale.Model.User.Doctor;
import Ospedale.Model.User.Patient;
import Ospedale.Model.User.User;
import Ospedale.Services.LoginService;
import java.util.HashMap;

public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    

    public Response login(LoginDTO dto) {
      try {
          User user = loginService.login(dto);
          HashMap<String,Object> data = new HashMap<>();
          data.put("user", user.serialize());
          data.put("userId", user.getId());
          data.put("userType", resolveUserType(user));
          
          
          return new Response("Login successful",Status.OK,data);
      } catch(RuntimeException e){
          return new Response(e.getMessage(),Status.NOT_FOUND);
      } 
    }

    private String resolveUserType(User user) {
        if (user instanceof Administrator) {
            return "admin";
        }
        if (user instanceof Patient) {
            return "patient";
        }
        if (user instanceof Doctor) {
            return "doctor";
        }
        return "user";
    }
}
