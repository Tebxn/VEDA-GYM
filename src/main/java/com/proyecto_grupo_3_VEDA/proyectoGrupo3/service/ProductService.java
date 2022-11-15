package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Product;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> listProduct() {
        return (List<Product>)productRepository.findAll();
    }
    
}
