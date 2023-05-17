/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author My PC
 */
public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;
    private Color color1;
    private Color color2;

    public TableActionCellEditor(TableActionEvent event, Color color1, Color color2) {
        super(new JCheckBox());
        this.event = event;
        this.color1= color1;
        this.color2= color2;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction action = new PanelAction();
        action.initEvent(event, row);
//        action.setBackground(jtable.getSelectionBackground());
        if (row % 2 == 0) {
            action.setBackground(color1);
        } else {
            action.setBackground(color2);
        }
        return action;
    }
}
