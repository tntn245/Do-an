/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author My PC
 */
public class TableActionCellRender extends DefaultTableCellRenderer {
    private Color color1;
    private Color color2;
    
    public TableActionCellRender(Color color1, Color color2){
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelAction action = new PanelAction();
        if (row % 2 == 0) {
            action.setBackground(color1);
        } else {
            action.setBackground(color2);
        }
        return action;
    }
}
