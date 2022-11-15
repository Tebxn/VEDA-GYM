package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Supplier;
import java.util.List;


public interface ISupplierService {
        
    public List<Supplier> getAllSupplier();
    public Supplier getSupplierById(long id); //Como buscar si es String
    public void saveSupplier(Supplier supplier);
    public void delete (long id); //Como buscar si es String

}
