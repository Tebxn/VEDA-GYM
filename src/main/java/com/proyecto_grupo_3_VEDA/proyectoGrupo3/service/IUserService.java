package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.User;
import java.util.List;


public interface IUserService {
    public List<User> getAllUser();
    public User getUserById(long id); //Como buscar si es String
    public void saveUser(User user);
    public void deleteUser(long id); //Como buscar si es String
}
