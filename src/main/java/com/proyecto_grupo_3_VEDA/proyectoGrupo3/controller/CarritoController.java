package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Item;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Product;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.IItemService;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class CarritoController {
    
    @Autowired
    private IItemService itemService;
    @Autowired
    private IProductService productService;
    
    @GetMapping("/carrito/listado")
    public String inicio(Model model){
        var items = itemService.getAllItem();
        model.addAttribute("items", items);
        var carritoTotalVenta=0;
        for(Item i:items){
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal",carritoTotalVenta);
        return "/carritoListado";
    }
    
    @GetMapping("/carrito/agregar/{idArticulo}")
    public ModelAndView agregarArticulo(Model model, Item item){
        Item item2 = itemService.getItem(item);
        if(item2==null){
            Product articulo = productService.getProductById(item.getId());
            item2 = new Item(articulo);
        }
        itemService.saveItem(item2);
        var lista=itemService.getAllItem();
        var totalCarritos=0;
        var carritoTotalVenta=0;
        for(Item i:lista){
            totalCarritos+=i.getCantidad();
            carritoTotalVenta+=(i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listarItems",lista);
        model.addAttribute("listaTotal",totalCarritos);
        model.addAttribute("carritoTotal",carritoTotalVenta);
        return new ModelAndView("/fragmentosCarrito :: verCarrito");
    }
    
    @GetMapping("/carrito/modificar/{idArticulo}")
    public String modificarCarrito(Item item, Model model){
        item = itemService.getItem(item);
        model.addAttribute("item",item);
        return "carritoModificar";
    }
    
    @GetMapping("/carrito/eiminar/{idArticulo}")
    public String eliminarItem(Item item){
        itemService.deleteItem(item);
        return "redirect:/carrito/listado";
    }
    
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item){
        itemService.actualiza(item);
        return "redirect:/carrito/listado";
    }
    
    @GetMapping("facturar/carrito")
    public String facturarCarrito(){
        itemService.facturar();
        return "redirect:/";
    }
}
