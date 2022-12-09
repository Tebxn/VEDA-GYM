package com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Product implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    private String c_producto;
    private String producto_titulo;
    private String producto_descripcion;
    private String tipo_producto;
    private float inventario;
    private float precio;
    private boolean existencia;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private Supplier proveedor;

    
    @Column(name="ruta_imagen")
    private String ruta_imagen;
            
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getC_producto() {
        return c_producto;
    }

    public void setC_producto(String c_producto) {
        this.c_producto = c_producto;
    }

    public String getProducto_titulo() {
        return producto_titulo;
    }

    public void setProducto_titulo(String producto_titulo) {
        this.producto_titulo = producto_titulo;
    }
    
    public String getProducto_descripcion() {
        return producto_descripcion;
    }

    public void setProducto_descripcion(String producto_descripcion) {
        this.producto_descripcion = producto_descripcion;
    }
    
    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public float getInventario() {
        return inventario;
    }

    public void setInventario(float inventario) {
        this.inventario = inventario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecioa(float precio) {
        this.precio = precio;
    }

    public Supplier getProveedor() {
        return proveedor;
    }

    public void setProveedor(Supplier proveedor) {
        this.proveedor = proveedor;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
    
    public Boolean getExistencia() {
        return existencia;
    }

    public void setExistencian(boolean existencia) {
        this.existencia = existencia;
    }
    
}
