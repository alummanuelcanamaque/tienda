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
public class MarcaList{
    private static List<Marca> listaMarca = new ArrayList();
    
    public static Object[] getArray(){
        return listaMarca.toArray();
    }
    
    public static void addMarca(Marca marca){
        listaMarca.add(marca);
    }
    
    public static Marca getMarcaByCodMarca(int codMarca){
        for(Marca m : listaMarca){
            if(m.getCodMarca()==codMarca){
                return m;
            }
        }
        return null;
    }
    
    public static int size(){
        return listaMarca.size();
    }    
    
    public static Marca getMarcaAt(int index){
        return listaMarca.get(index);
    }
    
    public static void deleteMarcaAt(int index){
        listaMarca.remove(index);
    }
    
//      Metodo para actualizar el array de marcas
//    public static void updateMarca(){
//        ResultSet rs = BaseDatos.ejecutar("select * from marca");
//        
//        try {
//            while (rs.next()) {
//                int codMarca = rs.getInt("CodMarca");
//                String nombre = rs.getString("Nombre");
//                
//                addMarca(codMarca, nombre);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Error al actualizar marcas");
//        }
//    }
    
    public static void updateMarcaQuery(){        
        listaMarca = BaseDatos.ejercutarQuery("Marca.findAll").getResultList();
    }
}
