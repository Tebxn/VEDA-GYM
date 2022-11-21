package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Human;
import java.util.List;


public interface IHumanService {
    public List<Human> getAllHuman();
    public Human getHumanById(long id); //Como buscar si es String
    public void saveHuman(Human human);
    public void deleteHuman(long id); //Como buscar si es String
    public Human findByNombre(String nombre);
}
