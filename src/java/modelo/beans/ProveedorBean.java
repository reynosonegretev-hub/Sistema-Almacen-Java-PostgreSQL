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
import modelo.pojos.Proveedor;

/**
 *
 * @author alejandromontesmoreno
 */
@ManagedBean
//Se agregó el scope o alcance del objeto en la interacción cliente-servidor
@ViewScoped
public class ProveedorBean implements Serializable {

    private Proveedor proveedor = new Proveedor();
    private List<Proveedor> listaProveedores= new ArrayList<>();
    private Proveedor proveedorSeleccionado = new Proveedor();
    private Proveedor proveedorEditar = new Proveedor();
    
    public void limpiarProveedor() {
        this.proveedor = new Proveedor();
    }

    /**
     * Agrega un registro a la tabla de producto utilizando un procedimiento
     * almacenado
     */
    public void insertarProveedor() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.sp_agregar_proveedor(?,?,?,?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setString(1, this.proveedor.getClave_proveedor());
                instruccion.setString(2, this.proveedor.getDescripcion_proveedor());
                instruccion.setDouble(3, this.proveedor.getPrecio_proveedor());
                instruccion.setString(4, this.proveedor.getCiudad_proveedor());
                instruccion.executeUpdate();
                System.out.println("El proveedor ha sido registrado");
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
    public void modificarProveedor() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.sp_modificar_proveedor(?,?,?,?,?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setInt(1, this.proveedor.getId_proveedor());
                instruccion.setString(2, this.proveedor.getClave_proveedor());
                instruccion.setString(3, this.proveedor.getDescripcion_proveedor());
                instruccion.setDouble(4, this.proveedor.getPrecio_proveedor());
                instruccion.setString(5, this.proveedor.getCiudad_proveedor());
                instruccion.executeUpdate();
                System.out.println("El proveedor ha sido actualizado");
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
    public void eliminarProveedor() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.sp_eliminar_proveedor(?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setInt(1, this.proveedorSeleccionado.getId_proveedor());

                instruccion.executeUpdate();
                System.out.println("El proveedor ha sido eliminado");
            } catch (SQLException e) {
                System.out.println("Error:" + e);
            }

        } else {
            System.out.println("No hay conexión con la BD");
        }
        ConexionBD.cerrarConexion();
      
    }

    public void buscarProveedor() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "call almacen.fn_listar_proveedores(?)";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                instruccion.setInt(1, this.proveedorSeleccionado.getId_proveedor());

                instruccion.executeUpdate();
                System.out.println("El proveedor ha sido encontrado");
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
    public void listarProveedores() {
        if (ConexionBD.establecerConexion()) {
            try {
                String sql = "select * FROM almacen.fn_listar_proveedores()";
                PreparedStatement instruccion = ConexionBD.getConexion().prepareStatement(sql);
                ResultSet consulta = instruccion.executeQuery();
                /*
                 * Extraer los datos de la consulta y pasarlos a la lista 
                 */
                this.listaProveedores = new ArrayList<>();
                while (consulta.next()) {
                    Proveedor prod = new Proveedor();

                    prod.setId_proveedor(consulta.getInt("id_proveedor"));
                    prod.setClave_proveedor(consulta.getString("clave_proveedor"));
                    prod.setDescripcion_proveedor(consulta.getString("descripcion_proveedor"));
                    prod.setPrecio_proveedor(consulta.getDouble("precio_proveedor"));
                    prod.setCiudad_proveedor(consulta.getString("ciudad_proveedor"));

                    this.listaProveedores.add(prod);
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
    public void recuperarDatos(Proveedor prod) {
        this.proveedor.setId_proveedor(prod.getId_proveedor());
        this.proveedor.setClave_proveedor(prod.getClave_proveedor());
        this.proveedor.setDescripcion_proveedor(prod.getDescripcion_proveedor());
        this.proveedor.setPrecio_proveedor(prod.getPrecio_proveedor());
        this.proveedor.setCiudad_proveedor(prod.getCiudad_proveedor());
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public Proveedor getProveedorEditar() {
        return proveedorEditar;
    }

    public void setProveedorEditar(Proveedor proveedorEditar) {
        this.proveedorEditar = proveedorEditar;
    }

    
    
   
}
