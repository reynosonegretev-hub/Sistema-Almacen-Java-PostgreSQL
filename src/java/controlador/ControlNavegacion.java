/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author graci
 */
@ManagedBean
public class ControlNavegacion {
    /**
     * Redirige a la página de productos
     * @return cadena para faces config
     */
    public String irProductos(){
        return "productos";
    }
    
    public String irProveedores(){
        return "proveedores";
    }
    /**
     * Redirige a la página de menú
     * @return cadena para faces config
     */
    public String irMenu(){
        return "regresar";
    }
    
}
