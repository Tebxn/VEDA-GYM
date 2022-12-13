package com.veda_tienda_2.repository;

import com.veda_tienda_2.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer>{
    
}
