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
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(long cliente_cedula) {
        return userRepository.findById(cliente_cedula).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long cliente_cedula) {
        userRepository.deleteById(cliente_cedula);
    }
}
