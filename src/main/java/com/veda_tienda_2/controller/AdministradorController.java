package com.veda_tienda_2.controller;

import com.veda_tienda_2.model.Orden;
import com.veda_tienda_2.model.Producto;
import com.veda_tienda_2.service.OrdenService;
import com.veda_tienda_2.service.ProductoService;
import com.veda_tienda_2.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired OrdenService ordenService;
    
    @GetMapping("")
    public String home(Model model){
            
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
        
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
	model.addAttribute("ordenes", ordenService.findAll());
	return "administrador/ordenes";
	}
    
    @GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
	log.info("Id de la orden {}",id);
	Orden orden= ordenService.findById(id).get();
		
	model.addAttribute("detalles", orden.getDetalle());
		
	return "administrador/detalleorden";
	}
        
    @GetMapping("productohomeadmin/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        log.info("Id producto enviado como parámetro {}", id);
	Producto producto = new Producto();
	Optional<Producto> productoOptional = productoService.get(id);
	producto = productoOptional.get();
	model.addAttribute("producto", producto);
        
        return "administrador/productohomeadmin";
    }
}
