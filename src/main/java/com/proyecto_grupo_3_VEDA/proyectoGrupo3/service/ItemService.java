package com.proyecto_grupo_3_VEDA.proyectoGrupo3.service;
import com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity.Item;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService {
    
    

    @Override
    public List<Item> getAllItem() {
        return listaItems;
    }

    @Override
    public void saveItem(Item item) {
        boolean existe = false;
        for(Item c : listaItems){
            if(Objects.equals(c.getId(), item.getId())){
                if(c.getCantidad()< item.getInventario()){
                    c.setCantidad(c.getCantidad()+1);
                }
            }
        }
        if(!existe){
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void deleteItem(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item c : listaItems){
            ++posicion;
            if(Objects.equals(c.getId(), item.getId())){
                existe = true;
                break;
            }
        }
        if(existe){
            listaItems.remove(posicion);
        }
    }
    
    @Override
    public Item getItem(Item item){
        for(Item c : listaItems){
            if(Objects.equals(c.getId(),item.getId())){
                return c;
            }
        }
        return null;
    }
    
    @Override
    public void actualiza(Item item) {
      for(Item i : listaItems){
          if(Objects.equals(i.getId(), item.getId())){
              i.setCantidad(item.getCantidad());
              break;
          }
      }  
    }

    @Override
    public void facturar() {
        for(Item i : listaItems){
            //proceso facturacion
        }
        listaItems.clear();
    }
}
