package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.User;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("/userList")
    public String index(Model model){
        List<User> listUsers = userService.getAllUser();
        model.addAttribute("titleUser", "Tabla Usuarios");
        model.addAttribute("users", listUsers);
        return "userList";
    }
    
    @GetMapping("/createAccount")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "createAccount";
    }
    
    @PostMapping("/saveAccount")
    public String saveUser(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/supplierList"; //cambiar esto
    }
    
    @GetMapping("/deleteUser/{id}") //ARREGLAR
   public String deleteUser(@PathVariable("id") Long idUser){
       userService.deleteUser(idUser);
       return "redirect:/userList";
   }
   
   @GetMapping("/returnHome")
    public String returnHome(){
        return "home";
    }

}
