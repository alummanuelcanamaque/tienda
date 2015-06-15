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
public class CategoriaList {
    private static List<Categoria> listaCategoria;    
    
    public static Object[] getArray(){
        return listaCategoria.toArray();
    }
    
    public static void addCategoria(Categoria categoria){
         listaCategoria.add(categoria);
    }
    
    public static Categoria getCategoriaByCodCategoria(int codCategoria){
        for(Categoria c : listaCategoria){
            if(c.getCodCategoria()==codCategoria){
                return c;
            }
        }
        return null;
    }
    
    public static int size(){
        return listaCategoria.size();
    }    
    
    public static Categoria getCategoriaAt(int index){
        return listaCategoria.get(index);
    }
    
    public static void deleteCategoriaAt(int index){
        listaCategoria.remove(index);
    }
    
//      Metodo para actualizar el array de categorias
//    public static void updateCategoria(){
//        listaCategoria.clear();
//        ResultSet rs = BaseDatos.ejecutar("select * from categoria");
//        
//        try {
//            while (rs.next()) {
//                int codCategoria = rs.getInt("CodCategoria");
//                String nombre = rs.getString("Nombre");
//                
//                addCategoria(codCategoria, nombre);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar categorias");
//        }
//    }
    
    public static void updateCategoriaQuery(){        
        listaCategoria = BaseDatos.ejercutarQuery("Categoria.findAll").getResultList();
    }
}
