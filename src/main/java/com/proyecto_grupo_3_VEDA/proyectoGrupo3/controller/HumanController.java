package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Human;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.IHumanService;

@Controller
public class HumanController {
    
    @Autowired
    private IHumanService userService;
    
    @GetMapping("/userList")
    public String index(Model model){
        List<Human> listUsers = userService.getAllHuman();
        model.addAttribute("titleUser", "Tabla Usuarios");
        model.addAttribute("users", listUsers);
        return "userList";
    }
    
    @GetMapping("/createAccount")
    public String createUser(Model model){
        model.addAttribute("user", new Human());
        return "createAccount";
    }
    
    @PostMapping("/saveAccount")
    public String saveUser(@ModelAttribute Human user){
        userService.saveHuman(user);
        return "redirect:/supplierList"; //cambiar esto
    }
    
    @GetMapping("/deleteUser/{id}") //ARREGLAR
   public String deleteUser(@PathVariable("id") Long idUser){
       userService.deleteHuman(idUser);
       return "redirect:/userList";
   }
   
   @GetMapping("/returnHome")
    public String returnHome(){
        return "home";
    }

}
