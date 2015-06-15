/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders;

import java.text.DateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Manuel Cañamaque
 */
public class FechaRender extends DefaultTableCellRenderer{

    @Override
    protected void setValue(Object value) {
        DateFormat formato = DateFormat.getDateInstance();
        setText(formato.format(value));
    }
}
