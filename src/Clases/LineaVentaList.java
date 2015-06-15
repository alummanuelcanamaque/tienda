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
public class LineaVentaList {
    private static List<Lineaventa> listaLineaVenta = new ArrayList();
    
    public static Object[] getArray(){
        return listaLineaVenta.toArray();
    }
    
    public static void addLineaVenta(Lineaventa lineaVenta){
         listaLineaVenta.add(lineaVenta);
    }
    public static Lineaventa getLineaVentaByCodlineaVenta(int codLineaVenta){
        for(Lineaventa lv : listaLineaVenta){
            if(lv.getCodLineaVenta()==codLineaVenta){
                return lv;
            }
        }
        return null;
    }
    
    public static int size(){
        return listaLineaVenta.size();
    }    
    
    public static Lineaventa getLineaVentaAt(int index){
        return listaLineaVenta.get(index);
    }
    
    public static void deleteLineaVentaAt(int index){
        listaLineaVenta.remove(index);
    }
    
//      Metodo para actualizar el array de LineasVentas
//    public static void updateLineaVenta(){
//        ResultSet rs = BaseDatos.ejecutar("select * from Lineaventa");
//        
//        try {
//            while (rs.next()) {
//                int codLineaVenta = rs.getInt("CodLineaVenta");
//                int venta = rs.getInt("Venta");
//                int articulo = rs.getInt("Articulo");
//                int cantidad = rs.getInt("Cantidad");
//                double importeLineaVenta = rs.getDouble("ImporteLineaVenta");
//                
//                addLineaVenta(codLineaVenta, VentaList.getVentaByCodVenta(venta), ArticuloList.getArticuloByCodArticulo(articulo), cantidad, importeLineaVenta);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar clientes");
//        }
//    }
    
    public static void updateLineaVentaQuery(){        
        listaLineaVenta = BaseDatos.ejercutarQuery("Lineaventa.findAll").getResultList();
    }
}
