package com.veda_tienda_2.controller;

import com.veda_tienda_2.model.Producto;
import com.veda_tienda_2.model.Usuario;
import com.veda_tienda_2.service.ProductoService;
import com.veda_tienda_2.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "productos/show";
    }
    
    
    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }
    
    @PostMapping("/save")
	public String save(Producto producto, HttpSession session) {
		LOGGER.info("Este es el objeto producto {}",producto);
		
		
                Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		producto.setUsuario(u);	
		
		
		productoService.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, HttpSession session) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
                Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		producto.setUsuario(u);	
		producto= optionalProducto.get();
		
		LOGGER.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
//	@PostMapping("/update")
//	public String update(Producto producto){
//            Producto p = new Producto();
//            
//            p=productoService.get(producto.getId()).get();
//            
//            producto.setUsuario(p.getUsuario());
//            
//            productoService.update(producto);		
//        return "redirect:/productos";
//	}
        @PostMapping("/update") //pierde el id_usuario
	public String update(Producto producto) {
		Producto p= new Producto();
		p=productoService.get(producto.getId()).get();
		
                producto.setUsuario(p.getUsuario());
		productoService.update(producto);		
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
            productoService.delete(id);
	return "redirect:/productos";
	}
}
