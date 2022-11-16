package com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "proveedor")
public class Supplier implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;
    private String c_proveedor;
    private String proveedor_nombre; 
    private String cedula_juridica;
    private String proveedor_email;
    private String proveedor_telefono;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getC_proveedor() {
        return c_proveedor;
    }

    public void setC_proveedor(String c_proveedor) {
        this.c_proveedor = c_proveedor;
    }

    public String getProveedor_nombre() {
        return proveedor_nombre;
    }

    public void setProveedor_nombre(String proveedor_nombre) {
        this.proveedor_nombre = proveedor_nombre;
    }

    public String getCedula_juridica() {
        return cedula_juridica;
    }

    public void setCedula_juridica(String cedula_juridica) {
        this.cedula_juridica = cedula_juridica;
    }

    public String getProveedor_email() {
        return proveedor_email;
    }

    public void setProveedor_email(String proveedor_email) {
        this.proveedor_email = proveedor_email;
    }

    public String getProveedor_telefono() {
        return proveedor_telefono;
    }

    public void setProveedor_telefono(String proveedor_telefono) {
        this.proveedor_telefono = proveedor_telefono;
    }

    
}
