package com.proyecto_grupo_3_VEDA.proyectoGrupo3.repository;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Long>{   
}