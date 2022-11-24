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
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
    
}
