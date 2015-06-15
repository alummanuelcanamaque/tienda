/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import BaseDatos.BaseDatos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Manuel Cañamaque
 */
public class ClienteList {
    private static List<Cliente> listaCliente = new ArrayList();    
    
    public static Object[] getArray(){
        return listaCliente.toArray();
    }
    
    public static void addCliente(Cliente cliente){
        listaCliente.add(cliente);
    }
    
    public static Cliente getClienteByCodCliente(int codCliente){
        for(Cliente c : listaCliente){
            if(c.getCodCliente()==codCliente){
                return c;
            }
        }
        return null;
    }
    
    public static int size(){
        return listaCliente.size();
    }    
    
    public static Cliente getClienteAt(int index){
        return listaCliente.get(index);
    }
    
    public static void deleteClienteAt(int index){
        listaCliente.remove(index);
    }
    
    
//      Metodo para actualizar el array de clientes
//    public static void updateCliente(){
//        ResultSet rs = BaseDatos.ejecutar("select * from cliente");
//        
//        try {
//            while (rs.next()) {
//                int codCliente = rs.getInt("CodCliente");
//                String nombre = rs.getString("Nombre");
//                String apellidos = rs.getString("Apellidos");
//                String dni = rs.getString("Dni");
//                Date fechaNacimiento = rs.getDate("FechaNacimiento");
//                String direccion = rs.getString("Direccion");
//                String localidad = rs.getString("Localidad");
//                int telefono = rs.getInt("Telefono");
//                
//                addCliente(codCliente, nombre, apellidos, dni, fechaNacimiento, direccion, localidad, telefono);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar clientes");
//        }
//    }
    
    public static void updateClienteQuery(){        
        listaCliente = BaseDatos.ejercutarQuery("Cliente.findAll").getResultList();
    }
}
