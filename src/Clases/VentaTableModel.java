/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigDecimal;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Cañamaque
 */
public class VentaTableModel extends AbstractTableModel {
    
    private int columnCount = 5;

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
    
    @Override
    public int getRowCount() {
        return VentaList.size();
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
                contenido = VentaList.getVentaAt(rowIndex).getCliente().getNombre()
                        + ", " + VentaList.getVentaAt(rowIndex).getCliente().getApellidos();
                break;
            case 1:
                contenido = VentaList.getVentaAt(rowIndex).getFechaVenta();
                break;
            case 2:
                contenido = VentaList.getVentaAt(rowIndex).getIva();
                break;
            case 3:
                contenido = VentaList.getVentaAt(rowIndex).getImporteVenta();
                break;
            case 4:
                contenido = VentaList.getVentaAt(rowIndex).getImporteVentaConIva();
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
                name = "Cliente";
                break;
            case 1:
                name = "FechaVenta";
                break;
            case 2:
                name = "Iva";
                break;
            case 3:
                name = "ImporteVenta";
                break;
            case 4:
                name = "ImporteVentaConIva";
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
            case 2:
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Venta venta = VentaList.getVentaAt(rowIndex);
        switch(columnIndex){
            case 0:
                venta.setCliente((Cliente)(aValue));
                break;
            case 2:
                venta.setIva(Integer.valueOf((String)(aValue)));
                break;
            case 3:
                venta.setImporteVenta(BigDecimal.valueOf(Double.valueOf((String)(aValue))));
                break;
            case 4:
                venta.setImporteVentaConIva(BigDecimal.valueOf(Double.valueOf((String)(aValue))));
        }
    }
}
