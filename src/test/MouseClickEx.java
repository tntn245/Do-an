/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author My PC
 */
public class MouseClickEx {
  public static void main(String [] args) {
 
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("MouseClickExample Example");
    frame.setSize(350,150);
    frame.setLayout(new FlowLayout());
 
    final JLabel label = new JLabel("test: ");
    JButton btn = new JButton("Click");
    frame.add(label);
    frame.add(btn);
 
    btn.addMouseListener(new MouseListener() {
        public void mousePressed(MouseEvent me) { }
        public void mouseReleased(MouseEvent me) { }
        public void mouseEntered(MouseEvent me) { }
        public void mouseExited(MouseEvent me) { }
        public void mouseClicked(MouseEvent me) { 
          if(me.getButton() == MouseEvent.BUTTON1) {
            label.setText("Left Click!");
          }
          if(me.getButton() == MouseEvent.BUTTON2) {
            label.setText("Middle Click!");
          }
          if(me.getButton() == MouseEvent.BUTTON3) {
            label.setText("Right Click!");
          }
        }
    });
    
//    frame.addMouseListener(new MouseListener() {
//        public void mousePressed(MouseEvent me) { }
//        public void mouseReleased(MouseEvent me) { }
//        public void mouseEntered(MouseEvent me) { }
//        public void mouseExited(MouseEvent me) { }
//        public void mouseClicked(MouseEvent me) { 
//          if(me.getButton() == MouseEvent.BUTTON1) {
//            label.setText("Left Click!");
//          }
//          if(me.getButton() == MouseEvent.BUTTON2) {
//            label.setText("Middle Click!");
//          }
//          if(me.getButton() == MouseEvent.BUTTON3) {
//            label.setText("Right Click!");
//          }
//        }
//    });
 
    frame.setVisible(true);			
  }
}
