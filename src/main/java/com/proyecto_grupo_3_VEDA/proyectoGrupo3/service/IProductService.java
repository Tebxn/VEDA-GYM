package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Product;
import java.util.List;


public interface IProductService {
    public List<Product> getAllProduct();
    public Product getProductById(long id); //Como buscar si es String
    public void saveProduct(Product product);
    public void deleteProduct(long id); //Como buscar si es String
}
