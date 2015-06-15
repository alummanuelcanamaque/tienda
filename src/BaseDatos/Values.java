/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

/**
 *
 * @author Manuel Cañamaque
 */
public class Values {
    //Datos maximo caracteres tabla Cliente
    public static final int nombre_Cliente = 20;
    public static final int apellidos_Cliente = 40;
    public static final int dni_Cliente = 9;
    public static final int fechaNacimiento_Cliente = 40;
    public static final int direccion_Cliente = 30;
    public static final int localidad_Cliente = 30;
    public static final int telefono_Cliente = 9;
    
     //Datos maximo caracteres tabla Proveedor
    public static final int nombre_Proveedor = 40;
    public static final int nif_Proveedor = 9;
    public static final int direccion_Proveedor = 30;
    public static final int telefono_Proveedor = 9;
    
     //Datos maximo caracteres tabla Categoria
    public static final int nombre_Categoria = 40;
    
     //Datos maximo caracteres tabla Marca
    public static final int nombre_Marca = 40;
    
    //Datos maximo caracteres tabla Articulo
    public static final int nombre_Articulo = 20;
    public static final int marca_Articulo = 40;
    public static final int descripcion_Articulo = 50;
    public static final int existencias_Articulo = 10;
    public static final int precio_Articulo = 16;
    public static final int proveedor_Articulo = 40;
    public static final int categoria_Articulo = 40;
    
    //Datos maximo caracteres tabla Venta
    public static final int cliente_Venta = 60;
    public static final int fechaVenta_Venta = 40;
    public static final int iva_Venta = 2;
    public static final int importeVenta_Venta = 16;
    public static final int importeVentaConIva_Venta = 16;
    
    //Datos maximo caracteres tabla LineaVenta
    public static final int codLineaVenta_LineaVenta = 5;
    public static final int venta_LineaVenta = 4;
    public static final int articulo_LineaVenta = 40;
    public static final int cantidad_LineaVenta = 3;    
    public static final int importeLineaVenta_LineaVenta = 16;
    
}
