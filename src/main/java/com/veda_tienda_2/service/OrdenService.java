package com.veda_tienda_2.service;

import com.veda_tienda_2.model.Orden;
import com.veda_tienda_2.model.Usuario;
import java.util.List;
import java.util.Optional;


public interface OrdenService {
    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden(); //GENERAR CONSECUTIVO
    List<Orden> findByUsuario (Usuario usuario);
    Optional<Orden>findById(Integer id);
    
}
