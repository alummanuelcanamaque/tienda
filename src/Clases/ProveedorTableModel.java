/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Cañamaque
 */
public class ProveedorTableModel extends AbstractTableModel {
    private int columnCount = 4;
    
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
    
    @Override
    public int getRowCount() {
        return ProveedorList.size();
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
                contenido = ProveedorList.getProveedorAt(rowIndex).getNombre();
                break;
            case 1:
                contenido = ProveedorList.getProveedorAt(rowIndex).getNif();
                break;
            case 2:
                contenido = ProveedorList.getProveedorAt(rowIndex).getDireccion();
                break;
            case 3:
                contenido = ProveedorList.getProveedorAt(rowIndex).getTelefono();
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
                name = "Nif";
                break;
            case 2:
                name = "Direccion";
                break;
            case 3:
                name = "Telefono";
                break;
            default:
                return null;
        }
        return name;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Proveedor proveedor = ProveedorList.getProveedorAt(rowIndex);
        switch(columnIndex){
            case 0:
                proveedor.setNombre((String)(aValue));
                break;
            case 1:
                proveedor.setNif((String)(aValue));
                break;
            case 2:
                proveedor.setDireccion((String)(aValue));
                break;
            case 3:
                proveedor.setTelefono(Integer.valueOf((String)(aValue)));
                break;
        }
    }

    

}
