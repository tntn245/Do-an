/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author My PC
 */
public class tabletest extends JFrame {
   private JTable table;
   private JScrollPane scrollPane;
   public tabletest() {
      setTitle("JTableButton Test");
      TableCellRenderer tableRenderer;
      table = new JTable(new JTableButtonModel());
      tableRenderer = table.getDefaultRenderer(JButton.class);
      table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
      scrollPane = new JScrollPane(table);
      add(scrollPane, BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setSize(400, 300);
      setVisible(true);
   }
   public static void main(String[] args) {
      new tabletest();
   }
}
class JTableButtonRenderer implements TableCellRenderer {
   private TableCellRenderer defaultRenderer;
   public JTableButtonRenderer(TableCellRenderer renderer) {
      defaultRenderer = renderer;
   }
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      if(value instanceof Component)
         return (Component)value;
         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
   }
}

class JTableButtonModel extends AbstractTableModel {
   private Object[][] rows = {{"Button1", new JButton("Button1")},{"Button2", new JButton("Button2")},{"Button3", new JButton("Button3")}, {"Button4", new JButton("Button4")}};
   private String[] columns = {"Count", "Buttons"};
   public String getColumnName(int column) {
      return columns[column];
   }
   public int getRowCount() {
      return rows.length;
   }
   public int getColumnCount() {
      return columns.length;
   }
   public Object getValueAt(int row, int column) {
      return rows[row][column];
   }
   public boolean isCellEditable(int row, int column) {
      return false;
   }
   public Class getColumnClass(int column) {
      return getValueAt(0, column).getClass();
   }
}