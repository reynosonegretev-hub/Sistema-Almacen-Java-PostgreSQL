/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.beans;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.ConexionBD;
import modelo.pojos.Producto;

/**
 *
 * @author alejandromontesmoreno
 */
@ManagedBean
//Se agregó el scope o alcance del objeto en la interacción cliente-servidor
@ViewScoped
public class ProductoBean implements Serializable {

    private Producto producto = new Producto();
    private List<Producto> listaProductos = new ArrayList<>();
    private Producto productoSeleccionado = new Producto();
    private Producto productoEditar = new Producto();

    public void limpiarProducto() {
        this.producto = new Producto();
    }

    /**
     * Agrega un registro a la tabla de producto utilizando un procedimiento
     * almacenado
     */
    public void insertarProducto() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.sp_agregar_producto(?,?,?,?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setString(1, this.producto.getClave());
                instruccion.setString(2, this.producto.getDescripcion());
                instruccion.setDouble(3, this.producto.getPrecio());
                instruccion.setInt(4, this.producto.getExistencia());
                instruccion.executeUpdate();
                System.out.println("El producto ha sido registrado");
            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }

        } else {
            System.out.println("No hay conexión con la BD");
        }
        ConexionBD.cerrarConexion();
    }

    /**
     * Actualiza un registro de la tabla de producto
     */
    public void modificarProducto() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.sp_modificar_producto(?,?,?,?,?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setInt(1, this.producto.getId_producto());
                instruccion.setString(2, this.producto.getClave());
                instruccion.setString(3, this.producto.getDescripcion());
                instruccion.setDouble(4, this.producto.getPrecio());
                instruccion.setInt(5, this.producto.getExistencia());
                instruccion.executeUpdate();
                System.out.println("El producto ha sido actualizado");
            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }

        } else {
            System.out.println("No hay conexión con la BD");
        }
        ConexionBD.cerrarConexion();
    }

    /**
     * Eliminar un registro de la tabla producto
     */
    public void eliminarProducto() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.sp_eliminar_producto(?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setInt(1, this.productoSeleccionado.getId_producto());

                instruccion.executeUpdate();
                System.out.println("El producto ha sido eliminado");
            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }

        } else {
            System.out.println("No hay conexión con la BD");
        }
        ConexionBD.cerrarConexion();
    }

    /**
     * Recupera los registros de la tabla de productos
     */
    public void listarProductos() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "select * FROM almacen.fn_listar_productos()";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                ResultSet consulta = instruccion.executeQuery();
                /*
                 * Extraer los datos de la consulta y pasarlos a la lista 
                 */
                this.listaProductos = new ArrayList<>();
                while (consulta.next()) {
                    Producto prod = new Producto();

                    prod.setId_producto(consulta.getInt("id_producto"));
                    prod.setClave(consulta.getString("clave"));
                    prod.setDescripcion(consulta.getString("descripcion"));
                    prod.setExistencia(consulta.getInt("existencia"));
                    prod.setPrecio(consulta.getDouble("precio"));

                    this.listaProductos.add(prod);
                }

            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }

        } else {
            System.out.println("No hay conexión con la base de datos");
        }
        ConexionBD.cerrarConexion();
    }

    /**
     * Agregué este método para recibir los datos
     *
     * @param prod contiene los datos del producto seleccionado Los datos son
     * asignados al producto el cual es utilizado en el cuadro de diálogo de
     * modificar
     */
    public void recuperarDatos(Producto prod) {
        this.producto.setId_producto(prod.getId_producto());
        this.producto.setClave(prod.getClave());
        this.producto.setDescripcion(prod.getDescripcion());
        this.producto.setPrecio(prod.getPrecio());
        this.producto.setExistencia(prod.getExistencia());
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Producto getProductoEditar() {
        return productoEditar;
    }

    public void setProductoEditar(Producto productoEditar) {
        this.productoEditar = productoEditar;
    }
}
