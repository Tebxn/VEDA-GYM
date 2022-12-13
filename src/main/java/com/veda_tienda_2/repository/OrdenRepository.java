package com.veda_tienda_2.repository;

import com.veda_tienda_2.model.Orden;
import com.veda_tienda_2.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{
    List<Orden> findByUsuario (Usuario usuario);
}
