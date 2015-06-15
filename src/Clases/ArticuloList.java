/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import BaseDatos.BaseDatos;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Manuel Cañamaque
 */
public class ArticuloList {
    private static List<Articulo> listaArticulo;
    
    public static Object[] getArray(){
        return listaArticulo.toArray();
    }

    public static void addArticulo(Articulo articulo){
        listaArticulo.add(articulo);
    }
    
    public static Articulo getArticuloByCodArticulo(int codArticulo){
        for(Articulo a : listaArticulo){
            if(a.getCodArticulo()==codArticulo){
                return a;
            }
        }
        return null;
    }
    
    public static int size(){
        return listaArticulo.size();
    }    
    
    public static Articulo getArticuloAt(int index){
        return listaArticulo.get(index);
    }
    
    public static void deleteArticuloAt(int index){
        listaArticulo.remove(index);
    }
    
//      Metodo para actualizar el array de articulos
//    public static void updateArticulo(){
//        ResultSet rs = BaseDatos.ejecutar("select * from articulo");
//        
//        try {
//            while (rs.next()) {
//                int codArticulo = rs.getInt("CodArticulo");
//                String nombre = rs.getString("Nombre");
//                int marca = rs.getInt("Marca");
//                String descripcion = rs.getString("Descripcion");
//                int existencias = rs.getInt("Existencias");
//                double precio = rs.getDouble("Precio");
//                int proveedor = rs.getInt("Proveedor");
//                int categoria = rs.getInt("Categoria");
//                
//                addArticulo(codArticulo, nombre, MarcaList.getMarcaByCodMarca(marca), descripcion, existencias, precio, ProveedorList.getProveedorByCodProveedor(proveedor), CategoriaList.getCategoriaByCodCategoria(categoria));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar articulos");
//        }
//    }
    
    public static void updateArticuloQuery(){
        listaArticulo = BaseDatos.ejercutarQuery("Articulo.findAll").getResultList();
    }
}
