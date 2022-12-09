package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Product;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.IProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @Autowired
    private IProductService productService;
    
    @GetMapping("/home")
    public String returnHome(){
        return "home";
    }
    
    @GetMapping("/products")    
        public String viewProducts(Model model){
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("products", productList); //sustituye personas por la lista de personas
        return "products";
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
}
