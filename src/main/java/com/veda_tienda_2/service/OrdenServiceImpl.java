package com.veda_tienda_2.service;

import com.veda_tienda_2.model.Orden;
import com.veda_tienda_2.model.Usuario;
import com.veda_tienda_2.repository.OrdenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    private OrdenRepository ordenRepository;
    
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }
    //FORMATO CONSECUTIVO 0000010
    public String generarNumeroOrden(){
        
        int numero=0;
        String numeroConcatenado="";
        
        List<Orden> ordenes = findAll();
        
        List<Integer> numeros=new ArrayList<Integer>();
        
        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));//recorrer los numeros de orden en string y pasarlos a integer
        
        if(ordenes.isEmpty()){
            numero=1;
        }else{
            numero = numeros.stream().max(Integer::compare).get(); //Obtiene el mayor numero de la lista
            numero++;
        }
        
        if(numero<10){
            numeroConcatenado = "000000000"+String.valueOf(numero);
        }else if(numero<100){
            numeroConcatenado = "00000000"+String.valueOf(numero);
        }else if(numero<1000){
            numeroConcatenado = "0000000"+String.valueOf(numero);
        }else if(numero<10000){
            numeroConcatenado = "000000"+String.valueOf(numero);
        } //Aqui se puede extender a lo necesario
        
        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }
    

    
    
}
