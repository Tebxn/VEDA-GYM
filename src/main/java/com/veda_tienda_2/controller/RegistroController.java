package com.veda_tienda_2.controller;

import com.veda_tienda_2.model.Orden;
import com.veda_tienda_2.model.Usuario;
import com.veda_tienda_2.service.OrdenService;
import com.veda_tienda_2.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/usuario")
public class RegistroController {
    
    private final Logger log = LoggerFactory.getLogger(RegistroController.class);
    
    @Autowired 
    private UsuarioService usuarioService;
    
    @Autowired
    private OrdenService ordenService;
    
    @GetMapping("/registro")
    public String registro(){
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario){
        log.info("Nuevo usuario: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }
    
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session){
        log.info("Informacion e login: {}",usuario);
        
        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        log.info("Usuario desde db: {}",user.get());
        
        if(user.isPresent()){
            session.setAttribute("idusuario",user.get().getId());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/home";
            }
        }
        else{
            log.info("usuarioinexistente");
        }
        return "redirect:/";
    }
    
   @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
	model.addAttribute("sesion", session.getAttribute("idusuario"));
		
	Usuario usuario= usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
	List<Orden> ordenes= ordenService.findByUsuario(usuario);
	log.info("ordenes {}", ordenes);
		
	model.addAttribute("ordenes", ordenes);
		
	return "usuario/compras";
    }
        
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        log.info("Id de la orden: {}", id);
        Optional<Orden> orden=ordenService.findById(id);
		
        model.addAttribute("detalles", orden.get().getDetalle());

        //session
        model.addAttribute("session", session.getAttribute("idusuario"));
            
        return "usuario/detallecompra";
    }
        
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session ) {
	session.removeAttribute("idusuario");
	return "redirect:/";
	}
}