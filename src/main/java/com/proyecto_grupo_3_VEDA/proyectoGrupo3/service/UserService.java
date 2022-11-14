package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.User;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> listUser() {
        return (List<User>)userRepository.findAll();
    }
    
}
