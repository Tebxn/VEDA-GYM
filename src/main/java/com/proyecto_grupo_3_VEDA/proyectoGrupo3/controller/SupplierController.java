package com.proyecto_grupo_3_VEDA.proyectoGrupo3.controller;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Supplier;
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
public class SupplierController {
    
    @Autowired
    private ISupplierService supplierService;
    
    @GetMapping("/supplierList")
    public String index(Model model){
        List<Supplier> listSupplier = supplierService.getAllSupplier();
        model.addAttribute("titleSupplier", "Tabla Proveedores");
        model.addAttribute("suppliers", listSupplier); //Arreglar no carga tabla proveedores es probable porque el id se esta usando varchar.
        return "supplierList";
    }
    
    @GetMapping("/addSupplier")
    public String createSupplier(Model model){
        model.addAttribute("supplier", new Supplier());
        return "addSupplier";
    }
    
    @PostMapping("/saveSupplier")
    public String saveSupplier(@ModelAttribute Supplier supplier){
        supplierService.saveSupplier(supplier);
        return "redirect:/supplierList";
    }
    
    @GetMapping("/deleteSupplier/{id}") //ARREGLAR
   public String deleteSupplier(@PathVariable("id") Long idSupplier){
       supplierService.deleteSupplier(idSupplier);
       return "redirect:/supplierList";
   }
   
//   @GetMapping("/editSupplier/{id}")
//   public String editarPersona(@PathVariable("id") Long idPersona, Model model){
//       Persona persona = personaService.getPersonaById(idPersona);
//       List<Pais> listaPaises = paisService.listCountry();
//       model.addAttribute("persona", persona);
//       model.addAttribute("paises", listaPaises);
//       return "crear";                                           FALTA
}
