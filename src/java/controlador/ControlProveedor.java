/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.beans.ProveedorBean;

/**
 *
 * @author alejandromontesmoreno
 */

@ManagedBean
@ViewScoped
public class ControlProveedor {

    @ManagedProperty(value = "#{proveedorBean}")
    private ProveedorBean proveedorBean;

    /**
     * Invoca al método para recuperar los productos
     */
    public void listarProveedores() {
        System.out.println("Listando proveedores...");
        this.proveedorBean.listarProveedores();
    }
        
    /**
     * Invoca el método para insertar un nuevo producto
     */
    public void insertarProveedor() {
        this.proveedorBean.insertarProveedor();
        this.proveedorBean.listarProveedores();
    }

    /**
     * Invoca al método de modificar producto
     */
    public void modificarProveedor() {
        System.out.println("Descripcion:"+this.proveedorBean.getProveedor().getDescripcion_proveedor());
        this.proveedorBean.modificarProveedor();
        this.proveedorBean.listarProveedores();
        System.out.println("El proveedor ha sido actualizado...");
    }

    /**
     * Método para llamar al método de eliminarProducto del Bean
     */
    public void eliminarProveedor() {
        this.proveedorBean.eliminarProveedor();
        this.proveedorBean.limpiarProveedor();
        System.out.println("El proveedor ha sido eliminado...");
    }

    public ProveedorBean getProveedorBean() {
        return proveedorBean;
    }

    public void setProveedorBean(ProveedorBean proveedorBean) {
        this.proveedorBean = proveedorBean;
    }

    

}