/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders;

import Clases.Articulo;
import Clases.Categoria;
import Clases.Cliente;
import Clases.Marca;
import Clases.Proveedor;
import Clases.Venta;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Manuel Cañamaque
 */
public class ComboBoxRender extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value.getClass() == Articulo.class){
            Articulo articulo = (Articulo) value;
            setText(articulo.getNombre());
        }
        if(value.getClass() == Categoria.class){
            Categoria categoria = (Categoria) value;
            setText(categoria.getNombre());
        }
        if(value.getClass() == Cliente.class){
            Cliente cliente = (Cliente) value;
            setText(cliente.getNombre()+", "+cliente.getApellidos());
        }
        if(value.getClass() == Marca.class){
            Marca marca = (Marca) value;
            setText(marca.getNombre());
        }
        if(value.getClass() == Proveedor.class){
            Proveedor proveedor = (Proveedor) value;
            setText(proveedor.getNombre());
        }
        if(value.getClass() == Venta.class){
            Venta venta = (Venta) value;
            setText(String.valueOf(venta.getCodVenta()));
        }
        if(isSelected){
            this.setBackground(Color.blue);
        }else{
            this.setBackground(Color.white);
        }     
        return this;
    }
    
    
}
