package Ospedale.Controller;

import Data.Storage;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.LoginDTO;
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
          data.put("user",user);
          
          
          return new Response("Login successful",Status.OK,data);
      } catch(RuntimeException e){
          return new Response(e.getMessage(),Status.NOT_FOUND);
      } 
    }
}
