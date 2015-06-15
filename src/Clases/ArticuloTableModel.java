/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Ventanas.Main;
import java.math.BigDecimal;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Cañamaque
 */
public class ArticuloTableModel extends AbstractTableModel {
    private int columnCount = 7;

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
    
    @Override
    public int getRowCount() {
        return ArticuloList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object contenido = null;
        switch(columnIndex){
            case 0:
                contenido = ArticuloList.getArticuloAt(rowIndex).getNombre();
                break;
            case 1:
                contenido = ArticuloList.getArticuloAt(rowIndex).getDescripcion();                
                break;
            case 2:
                contenido = ArticuloList.getArticuloAt(rowIndex).getMarca().getNombre();
                break;
            case 3:
                contenido = ArticuloList.getArticuloAt(rowIndex).getCategoria().getNombre();
                break;
            case 4:
                contenido = ArticuloList.getArticuloAt(rowIndex).getProveedor().getNombre();
                break;
            case 5:
                contenido = ArticuloList.getArticuloAt(rowIndex).getExistencias();                
                break;
            case 6:                
                contenido = ArticuloList.getArticuloAt(rowIndex).getPrecio();
                break;
            default:
                return null;
        }
        return contenido;
    }
    
    @Override
    public String getColumnName(int column){
        String name = "";
        switch(column){
            case 0:
                name = "Nombre";
                break;
            case 1:
                name = "Descripcion";
                break;
            case 2:
                name = "Marca";
                break;
            case 3:
                name = "Categoria";
                break;
            case 4:
                name = "Proveedor";
                break;
            case 5:
                name = "Existencias";
                break;
            case 6:
                name = "Precio";
                break;
            default:
                return null;
        }
        return name;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(Main.getRowSelected()==rowIndex){
        switch(columnIndex){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
        }else{
            return false;
        }
    }  
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Articulo articulo = ArticuloList.getArticuloAt(rowIndex);
        switch(columnIndex){
            case 0:
                articulo.setNombre((String)(aValue));
                break;
            case 1:
                articulo.setDescripcion((String)(aValue));
                break;
            case 2:
                articulo.setMarca((Marca)(aValue));
                break;
            case 3:
                articulo.setCategoria((Categoria)(aValue));
                break;
            case 4:
                articulo.setProveedor((Proveedor)(aValue));
                break;
            case 5:
                articulo.setExistencias(Integer.valueOf((String)(aValue)));
                break;
            case 6:
                articulo.setPrecio(BigDecimal.valueOf(Double.valueOf((String)(aValue))));                
        }
    }
    
}
