package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Supplier;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.repository.SupplierRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Override
    public List<Supplier> listSupplier() {
        return (List<Supplier>)supplierRepository.findAll();
    }
    
}
