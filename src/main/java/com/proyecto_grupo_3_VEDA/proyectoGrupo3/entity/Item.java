package com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity;

import lombok.Data;

@Data
public class Item extends Product{
    private int cantidad; //Almacena la cantidad de utems de un articulo
    
    public Item(){
    }
    
    public Item(Product product){
        super.setId(product.getId());
        super.setC_producto(product.getC_producto());
        super.setProducto_titulo(product.getProducto_titulo());
        super.setProducto_descripcion(product.getProducto_descripcion());
        super.setTipo_producto(product.getTipo_producto());
        super.setExistencia(product.getExistencia());
        super.setPrecio(product.getPrecio());
        this.cantidad = 0;
    }
}
