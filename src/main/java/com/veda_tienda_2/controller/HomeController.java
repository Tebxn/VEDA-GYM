package com.veda_tienda_2.controller;

import com.veda_tienda_2.model.DetalleOrden;
import com.veda_tienda_2.model.Orden;
import com.veda_tienda_2.model.Producto;
import com.veda_tienda_2.model.Usuario;
import com.veda_tienda_2.service.DetalleOrdenService;
import com.veda_tienda_2.service.OrdenService;
import com.veda_tienda_2.service.ProductoService;
import com.veda_tienda_2.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
@RequestMapping("/")
public class HomeController {
    
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private OrdenService ordenService;
    
    @Autowired
    private DetalleOrdenService detalleOrdenService;
    
    
    
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>(); //para almacenar detalles de la orden
    
    Orden orden = new Orden();
    
    @GetMapping("")
    private String homeSA(Model model, HttpSession session){
        
        log.info("Usuario recibido: {}",session.getAttribute("idusuario"));
        
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        
        //enviar session
        model.addAttribute("session", session.getAttribute("idusuario"));
        
        return "usuario/homeSA";
    }
    
    @GetMapping("/home")
    private String home(Model model, HttpSession session){
        
        log.info("Usuario recibido: {}",session.getAttribute("idusuario"));
        
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        
        //enviar session
        model.addAttribute("session", session.getAttribute("idusuario"));
        
        return "usuario/home";
    }
    
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        log.info("Id producto enviado como parámetro {}", id);
	Producto producto = new Producto();
	Optional<Producto> productoOptional = productoService.get(id);
	producto = productoOptional.get();
	model.addAttribute("producto", producto);
        
        return "usuario/productohome";
    }
    
    @GetMapping("productohomeSinAutenticar/{id}")
    public String productoHomeSinAutenticar(@PathVariable Integer id, Model model){
        log.info("Id producto enviado como parámetro {}", id);
	Producto producto = new Producto();
	Optional<Producto> productoOptional = productoService.get(id);
	producto = productoOptional.get();
	model.addAttribute("producto", producto);
        
        return "usuario/productohomeSinAutenticar";
    }
    
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
       DetalleOrden detalleOrden = new DetalleOrden();
	Producto producto = new Producto();
	double sumaTotal = 0;

	Optional<Producto> optionalProducto = productoService.get(id);
	log.info("Producto añadido: {}", optionalProducto.get());
	log.info("Cantidad: {}", cantidad);
	producto = optionalProducto.get();

	detalleOrden.setCantidad(cantidad);
	detalleOrden.setPrecio(producto.getPrecio());
	detalleOrden.setNombre(producto.getNombre());
	detalleOrden.setTotal(producto.getPrecio() * cantidad);
	detalleOrden.setProducto(producto);

        
        //validacion para evitar que el producto se agregue como otro

		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
        
        
        return "usuario/carrito";
    }
    @PostMapping("/cartSA")
    public String addCartSA(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
       DetalleOrden detalleOrden = new DetalleOrden();
	Producto producto = new Producto();
	double sumaTotal = 0;

	Optional<Producto> optionalProducto = productoService.get(id);
	log.info("Producto añadido: {}", optionalProducto.get());
	log.info("Cantidad: {}", cantidad);
	producto = optionalProducto.get();

	detalleOrden.setCantidad(cantidad);
	detalleOrden.setPrecio(producto.getPrecio());
	detalleOrden.setNombre(producto.getNombre());
	detalleOrden.setTotal(producto.getPrecio() * cantidad);
	detalleOrden.setProducto(producto);

        
        //validacion para evitar que el producto se agregue como otro

		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
        
        
        return "usuario/carritoSA";
    }
    
    //QUITAR UN PRODUCTO DEL CARRITO (se crea una lista nueva sin los elementos quitados)
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// creacion de la nueva lista
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}

		// poner la nueva lista con los productos restantes a detalles (nuestra lista anterior)
		detalles = ordenesNueva;

                //se realiza lo mismo que para el agregado
		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}
        
        @GetMapping("/getCart")
        public String getCart(Model model, HttpSession session){
            model.addAttribute("cart", detalles);
            model.addAttribute("orden", orden);
            
            //session
            model.addAttribute("session", session.getAttribute("idusuario"));
            
            return "usuario/carrito";
        }
        
        @GetMapping("/order")
        public String order(Model model, HttpSession session){
            
            Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
            
            model.addAttribute("cart", detalles);
            model.addAttribute("orden", orden);
            model.addAttribute("usuario", usuario);
            
            return "usuario/resumenorden";
        }
        
        //guardar la orden en base de datos
        @GetMapping("/saveOrder")
        public String saveOrder(HttpSession session){
            Date fechaCreacion = new Date();
            orden.setFecha_creacion(fechaCreacion);
            orden.setNumero(ordenService.generarNumeroOrden());
            
            //usuario
            Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
            orden.setUsuario(usuario);
            ordenService.save(orden);
            
            //guardarDetalles
            for(DetalleOrden dt:detalles){
                dt.setOrden(orden);
                detalleOrdenService.save(dt);
            }
            
            //limpiar valores del detalle y orden
            orden = new Orden();
            detalles.clear();
            
            return "redirect:/home";
        }
        
        @GetMapping("/orderSA")
            public String orderSA(){
            return "productos/orderSA";
    }
        
        //Lo hace por coincidencias (mayusculas diferentes ya no encuentra)
        @PostMapping("/search")
        public String buscarProducto(@RequestParam String nombre, Model model){
            log.info("Producto Buscado: {}",nombre);
            
            List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList()); //funciones java 8: se recorre la lista de productos se compara el nombre con el nombre recibido y los similares se meten a la lista
            model.addAttribute("productos",productos);
            
            return "usuario/home";
        }
    
        @PostMapping("/searchSA")
        public String buscarProductoSA(@RequestParam String nombre, Model model){
            log.info("Producto Buscado: {}",nombre);
            
            List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList()); //funciones java 8: se recorre la lista de productos se compara el nombre con el nombre recibido y los similares se meten a la lista
            model.addAttribute("productos",productos);
            
            return "/";
        }
}
