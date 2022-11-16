
package com.proyecto_grupo_3_VEDA.proyectoGrupo3.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cliente_cedula;
    private String cliente_nombre;
    private String cliente_apellido_1;
    private String cliente_apellido_2;
    private String cliente_email;
    private String cliente_direccion;
    private String cliente_telefono;
    private String password;

    public String getCliente_cedula() {
        return cliente_cedula;
    }

    public void setCliente_cedula(String cliente_cedula) {
        this.cliente_cedula = cliente_cedula;
    }

    public String getCliente_nombre() {
        return cliente_nombre;
    }

    public void setCliente_nombre(String cliente_nombre) {
        this.cliente_nombre = cliente_nombre;
    }

    public String getCliente_apellido_1() {
        return cliente_apellido_1;
    }

    public void setCliente_apellido_1(String cliente_apellido_1) {
        this.cliente_apellido_1 = cliente_apellido_1;
    }

    public String getCliente_apellido_2() {
        return cliente_apellido_2;
    }

    public void setCliente_apellido_2(String cliente_apellido_2) {
        this.cliente_apellido_2 = cliente_apellido_2;
    }

    public String getCliente_email() {
        return cliente_email;
    }

    public void setCliente_email(String cliente_email) {
        this.cliente_email = cliente_email;
    }

    public String getCliente_direccion() {
        return cliente_direccion;
    }

    public void setCliente_direccion(String cliente_direccion) {
        this.cliente_direccion = cliente_direccion;
    }

    public String getCliente_telefono() {
        return cliente_telefono;
    }

    public void setCliente_telefono(String cliente_telefono) {
        this.cliente_telefono = cliente_telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
