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
public class LineaVentaTableModel extends AbstractTableModel {
    
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
                contenido = LineaVentaList.getLineaVentaAt(rowIndex).getCodLineaVenta();
                break;
            case 1:
                contenido = LineaVentaList.getLineaVentaAt(rowIndex).getVenta().getCodVenta();
                break;
            case 2:
                contenido = LineaVentaList.getLineaVentaAt(rowIndex).getArticulo().getNombre();
                break;
            case 3:
                contenido = LineaVentaList.getLineaVentaAt(rowIndex).getCantidad();
                break;
            case 4:
                contenido = LineaVentaList.getLineaVentaAt(rowIndex).getImporteLineaVenta();
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
                name = "CodLineaVenta";
                break;
            case 1:
                name = "Venta";
                break;
            case 2:
                name = "Articulo";
                break;
            case 3:
                name = "Cantidad";
                break;
            case 4:
                name = "ImporteLineaVenta";
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
        Lineaventa lineaVenta = LineaVentaList.getLineaVentaAt(rowIndex);
        switch(columnIndex){
            case 1:
                lineaVenta.setVenta((Venta)(aValue));
                break;
            case 2:
                lineaVenta.setArticulo((Articulo)(aValue));
                break;
            case 3:
                lineaVenta.setCantidad(Integer.valueOf((String)(aValue)));
                break;
            case 4:
                lineaVenta.setImporteLineaVenta(BigDecimal.valueOf(Double.valueOf((String)(aValue))));
        }
    }
}