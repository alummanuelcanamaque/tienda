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
public class MarcaTableModel extends AbstractTableModel {
    private int columnCount = 2;

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
    
    @Override
    public int getRowCount() {
        return MarcaList.size();
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
                contenido = MarcaList.getMarcaAt(rowIndex).getCodMarca();
                break;
            case 1:
                contenido = MarcaList.getMarcaAt(rowIndex).getNombre();
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
                name = "CodMarca";
                break;
            case 1:
                name = "Nombre";
                break;
            default:
                return null;
        }
        return name;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 1:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Marca marca = MarcaList.getMarcaAt(rowIndex);
        switch(columnIndex){
            case 1:
                marca.setNombre((String)(aValue));
        }
    }
}
