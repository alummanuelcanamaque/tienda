/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Cañamaque
 */
public class ClienteTableModel extends AbstractTableModel {
    
    private int columnCount = 7;

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
    
    @Override
    public int getRowCount() {
        return ClienteList.size();
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
                contenido = ClienteList.getClienteAt(rowIndex).getNombre();
                break;
            case 1:
                contenido = ClienteList.getClienteAt(rowIndex).getApellidos();
                break;
            case 2:
                contenido = ClienteList.getClienteAt(rowIndex).getDni();
                break;
            case 3:
                contenido = ClienteList.getClienteAt(rowIndex).getFechaNacimiento();
                break;
            case 4:
                contenido = ClienteList.getClienteAt(rowIndex).getDireccion();
                break;
            case 5:
                contenido = ClienteList.getClienteAt(rowIndex).getLocalidad();
                break;
            case 6:
                contenido = ClienteList.getClienteAt(rowIndex).getTelefono();
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
                name = "Apellidos";
                break;
            case 2:
                name = "Dni";
                break;
            case 3:
                name = "Fecha Nacimiento";
                break;
            case 4:
                name = "Direccion";
                break;
            case 5:
                name = "Localidad";
                break;
            case 6:
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
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cliente cliente = ClienteList.getClienteAt(rowIndex);
        switch(columnIndex){
            case 0:
                cliente.setNombre((String)(aValue));
                break;
            case 1:
                cliente.setApellidos((String)(aValue));
                break;
            case 2:
                cliente.setDni((String)(aValue));
                break;
            case 4:
                cliente.setDireccion((String)(aValue));
                break;
            case 5:
                cliente.setLocalidad((String)(aValue));
                break;
            case 6:
                cliente.setTelefono(Integer.valueOf((String)(aValue)));               
        }
    }
    
}
