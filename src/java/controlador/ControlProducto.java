/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.beans.ProductoBean;

/**
 *
 * @author alejandromontesmoreno
 */
@ManagedBean
@RequestScoped
public class ControlProducto {

    @ManagedProperty(value = "#{productoBean}")
    private ProductoBean productoBean;

    /**
     * Invoca al método para recuperar los productos
     */
    public void listarProductos() {
        System.out.println("Listando productos...");
        this.productoBean.listarProductos();
    }

    /**
     * Invoca el método para insertar un nuevo producto
     */
    public void insertarProducto() {
        this.productoBean.insertarProducto();
        this.productoBean.listarProductos();
    }

    /**
     * Invoca al método de modificar producto
     */
    public void modificarProducto() {
        System.out.println("Descripcion:"+this.productoBean.getProducto().getDescripcion());
        this.productoBean.modificarProducto();
        this.productoBean.listarProductos();
        System.out.println("El producto ha sido actualizado...");
    }

    /**
     * Método para llamar al método de eliminarProducto del Bean
     */
    public void eliminarProducto() {
        this.productoBean.eliminarProducto();
        this.productoBean.listarProductos();
        System.out.println("El producto ha sido eliminado...");
    }

    public ProductoBean getProductoBean() {
        return productoBean;
    }

    public void setProductoBean(ProductoBean productoBean) {
        this.productoBean = productoBean;
    }

}