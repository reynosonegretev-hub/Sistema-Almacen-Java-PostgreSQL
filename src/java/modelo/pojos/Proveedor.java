/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import java.io.Serializable;

/**
 *
 * @author graci
 */

public class Proveedor implements Serializable {
    private int id_proveedor= 0;
    private String clave_proveedor = "";
    private String descripcion_proveedor = "";
    private String ciudad_proveedor = "";
    private double precio_proveedor = 0;
    

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getClave_proveedor() {
        return clave_proveedor;
    }

    public void setClave_proveedor(String clave_proveedor) {
        this.clave_proveedor = clave_proveedor;
    }

    public String getDescripcion_proveedor() {
        return descripcion_proveedor;
    }

    public void setDescripcion_proveedor(String descripcion_proveedor) {
        this.descripcion_proveedor = descripcion_proveedor;
    }

    public String getCiudad_proveedor() {
        return ciudad_proveedor;
    }

    public void setCiudad_proveedor(String ciudad_proveedor) {
        this.ciudad_proveedor = ciudad_proveedor;
    }

    public double getPrecio_proveedor() {
        return precio_proveedor;
    }

    public void setPrecio_proveedor(double precio_proveedor) {
        this.precio_proveedor = precio_proveedor;
    }
    

    
}
