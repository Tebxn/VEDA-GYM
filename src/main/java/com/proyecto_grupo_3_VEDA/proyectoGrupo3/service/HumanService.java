package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Human;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.repository.HumanRepository;


@Service
public class HumanService implements IHumanService {
    
    @Autowired
    private HumanRepository humanRepository;
    
    @Override
    public List<Human> getAllHuman() {
        return (List<Human>) humanRepository.findAll();
    }

    @Override
    public Human getHumanById(long id) {
        return humanRepository.findById(id).orElse(null);
    }

    @Override
    public void saveHuman(Human user) {
        humanRepository.save(user);
    }

    @Override
    public void deleteHuman(long id) {
        humanRepository.deleteById(id);
    }
    
    @Override
    public Human findByNombre(String nombre){
        return humanRepository.findByNombre(nombre);
    }
}
