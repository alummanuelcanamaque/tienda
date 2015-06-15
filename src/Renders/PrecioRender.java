/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders;

import java.text.NumberFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Manuel Cañamaque
 */
public class PrecioRender extends DefaultTableCellRenderer{
    @Override
    protected void setValue(Object value) {
        NumberFormat formato = NumberFormat.getCurrencyInstance();
        setText(formato.format(value));
        setHorizontalAlignment(RIGHT);
    }
    
    
}
