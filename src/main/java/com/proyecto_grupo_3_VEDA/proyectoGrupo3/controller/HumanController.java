package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Human;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Supplier;
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
    
    @GetMapping("/addUser")
    public String createUser(Model model){
        model.addAttribute("user", new Human());
        return "addUser";
    }
    
    @PostMapping("/saveAccount")
    public String saveUser(@ModelAttribute Human human){
        userService.saveHuman(human);
        return "redirect:/userList";
    }
    
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Long idHuman, Model model){
        Human human = userService.getHumanById(idHuman);
        List<Human> userList = userService.getAllHuman();
        model.addAttribute("user", human);
    return "addUser";
    }
    
    @GetMapping("/deleteUser/{id}")
   public String deleteUser(@PathVariable("id") Long idUser){
       userService.deleteHuman(idUser);
       return "redirect:/userList";
   }
   
   @GetMapping("/home")
    public String returnHome(){
        return "home";
    }
    
    @GetMapping("/products")    
    public String viewProducts(){
        return "products";
    }
    
    @GetMapping("/machines")    
    public String viewMachines(){
        return "maquinas";
    }
    
    @GetMapping("/props")    
    public String viewProps(){
        return "articulos";
    }
    
    @GetMapping("/contactUs")    
    public String viewContactUs(){
        return "contactUs";
    }
    @GetMapping("/aboutUs")    
    public String viewAboutUs(){
        return "aboutUs";
    }
    @GetMapping("/legal")    
    public String viewTermsAndConditions(){
        return "termsAndConditions";
    }
    
    @GetMapping("/management")    
    public String viewManagement(){
        return "management";
    }
    
    @GetMapping("/profile")    
    public String viewProfile(){
        return "profile";
    }
}
