/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Services;

import Data.UserRepository;
import Ospedale.DTO.LoginDTO;
import Ospedale.Model.User.User;

/**
 *
 * @author Manuel Ojeda
 */
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User login(LoginDTO dto){
        User user = userRepository.findUsername(dto.getUsername());
        
        if(user == null){
            throw new RuntimeException("Invalid username or password");
        }
        if (!user.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Invalid username or password");
 
        }
        return user;
    }
}
