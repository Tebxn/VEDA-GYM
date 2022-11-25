package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Product;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Supplier;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.IProductService;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.service.ISupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {
    
    @Autowired
    private IProductService productService;
    
    @Autowired
    private ISupplierService supplierService;
    
    @GetMapping("/productList")
    
    public String index(Model model){
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("titulo", "Products");//sustituye titulo por la tabla personas, model pasa la info al frond end
        model.addAttribute("products", productList); //sustituye personas por la lista de personas
        return "productList";
        
    }
    
    @GetMapping("/addProduct")
    public String crearPersona(Model model){
        List<Supplier> supplierList = supplierService.getAllSupplier();
        model.addAttribute("product", new Product());
        model.addAttribute("supplier", supplierList);
        return "addProduct";
    }
    
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product){
        productService.saveProduct(product);
        return "redirect:/productList";
    
    }
    
    @GetMapping("/editProduct/{id}")
    public String editarProduct(@PathVariable("id") Long idProduct, Model model){
        Product product = productService.getProductById(idProduct);
        List<Supplier> supplierList = supplierService.getAllSupplier();
        model.addAttribute("product", product);
        model.addAttribute("supplier", supplierList);
        return "addProduct";
    
    }
    
    @GetMapping("/delete/{id}")
    
    public String deleteProduct(@PathVariable("id")Long idProduct){
        productService.deleteProduct(idProduct);
        return "redirect:/productList";
    }
    }
