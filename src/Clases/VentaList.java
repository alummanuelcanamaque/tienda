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
public class VentaList {
    private static List<Venta> listaVenta = new ArrayList();
    
    public static Object[] getArray(){
        return listaVenta.toArray();
    }
    
    public static void addVenta(Venta venta){
        listaVenta.add(venta);
    }
    
    public static Venta getVentaByCodVenta(int codVenta){
        for(Venta v : listaVenta){
            if(v.getCodVenta()==codVenta){
                return v;
            }
        }
        return null;
    }
    public static int size(){
        return listaVenta.size();
    }    
    
    public static Venta getVentaAt(int index){
        return listaVenta.get(index);
    }
    
    public static void deleteVentaAt(int index){
        listaVenta.remove(index);
    }
    
//      Metodo para actualizar el array de ventas
//    public static void updateVenta(){
//        ResultSet rs = BaseDatos.ejecutar("select * from venta");
//        
//        try {
//            while (rs.next()) {
//                int codVenta = rs.getInt("CodVenta");
//                int cliente = rs.getInt("Cliente");
//                String fechaVenta = rs.getString("FechaVenta");
//                int iva = rs.getInt("Iva");
//                double importeVenta = rs.getDouble("ImporteVenta");
//                double importeVentaConIva = rs.getDouble("ImporteVentaConIva");
//                
//                addVenta(codVenta, ClienteList.getClienteByCodCliente(cliente), fechaVenta, iva, importeVenta, importeVentaConIva);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar clientes");
//        }
//    }
    
    public static void updateVentaQuery(){        
        listaVenta = BaseDatos.ejercutarQuery("Venta.findAll").getResultList();
    }
}
