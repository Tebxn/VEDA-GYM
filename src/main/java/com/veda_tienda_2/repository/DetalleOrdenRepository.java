package com.veda_tienda_2.repository;

import com.veda_tienda_2.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden,Integer>{
    
}
