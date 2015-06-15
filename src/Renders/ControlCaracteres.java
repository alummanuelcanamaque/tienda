/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renders;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Manuel Cañamaque
 */
public class ControlCaracteres extends PlainDocument{
    private int maxLength;

    public ControlCaracteres(int maxLength) {
        this.maxLength = maxLength;
    } 
    
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {        
        
        if(getLength() + str.length() <= maxLength){
            super.insertString(offs, str, a);
        }
    }
    
}
