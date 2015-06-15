/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import BaseDatos.BaseDatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Cañamaque
 */
public class ProveedorList {
    private static List<Proveedor> listaProveedor = new ArrayList();
    
    public static Object[] getArray(){
        return listaProveedor.toArray();
    }
    
    public static void addProveedor(Proveedor proveedor){
        listaProveedor.add(proveedor);
    }
    
    public static Proveedor getProveedorByCodProveedor(int codProveedor){
        for(Proveedor p : listaProveedor){
            if(p.getCodProveedor()==codProveedor){
                return p;
            }
        }
        return null;
    }
    
    
    public static int size(){
        return listaProveedor.size();
    }    
    
    public static Proveedor getProveedorAt(int index){
        return listaProveedor.get(index);
    }
    
    public static void deleteProveedorAt(int index){
        listaProveedor.remove(index);
    }
    
//      Metodo para actualizar el array de proveedores
//    public static void updateProveedor(){
//        ResultSet rs = BaseDatos.ejecutar("select * from proveedor");
//        
//        try {
//            while (rs.next()) {
//                int codProveedor = rs.getInt("CodProveedor");
//                String nombre = rs.getString("Nombre");
//                String nif = rs.getString("Nif");
//                String direccion = rs.getString("Direccion");
//                int telefono = rs.getInt("Telefono");
//                
//                addProveedor(codProveedor, nombre, nif, direccion, telefono);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar proveedores");
//        }
//    }
    
    public static void updateProveedorQuery(){        
        listaProveedor = BaseDatos.ejercutarQuery("Proveedor.findAll").getResultList();
    }
}  

