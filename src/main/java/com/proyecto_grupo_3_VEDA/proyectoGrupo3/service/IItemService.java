package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;

import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Item;
import java.util.ArrayList;
import java.util.List;


public interface IItemService {
    
    public List<Item> listaItems = new ArrayList<>();
    
    public List<Item> getAllItem();
    
    public void saveItem(Item item);
    
    public void deleteItem(Item item); 
    
    public Item getItem(Item item);
    
    public void actualiza(Item item);
   
    public void facturar();
}
