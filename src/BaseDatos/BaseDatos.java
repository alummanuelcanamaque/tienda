/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Manuel Cañamaque
 */
public class BaseDatos {   
    static Connection conexion = null;
    public static EntityManager entityManager = Persistence.createEntityManagerFactory("TiendaPU").createEntityManager();
    
    public static void conectar(String direccionBaseDatos, String usuario, String contraseña){
        
        //Registrar el driver MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("Error al obtener driver para la base de datos");
        }
        
        //Realizar la conexion con la base de datos
        try {
            conexion = DriverManager.getConnection(direccionBaseDatos,usuario, contraseña);            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos");
        }        
    }
    
    public static Object ejecutar(String sentencia){
        ResultSet rs = null;
        try {
            Statement stmt = conexion.createStatement();
            if((sentencia.toUpperCase().contains("UPDATE")) || (sentencia.toUpperCase().contains("DELETE")) || (sentencia.toUpperCase().contains("INSERT"))){
                int numero = stmt.executeUpdate(sentencia);
                return numero;
                
            }else{
                rs = stmt.executeQuery(sentencia);
                return rs;
            }            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al ejecutar sentencia");
            return null;
        }        
    }
    
    public static Query ejercutarQuery(String sentencia){        
        Query query = BaseDatos.entityManager.createNamedQuery(sentencia);        
        return query;
    }
    
}
