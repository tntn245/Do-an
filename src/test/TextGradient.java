/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author My PC
 */
public class TextGradient extends JPanel {
  Font font;
  FontMetrics fontMetrics;
  String str;
  TextGradient(String str, Font font) {
//    setSize(100, 100); 
//    setBackground(Color.white);
    this.font = font;    
    this.str = str;
    fontMetrics = getFontMetrics(font);
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    int w = getSize().width;
    int h = 20;

    g2D.setFont(font);
    GradientPaint gp = new GradientPaint( //truc y tu tren xuong!!!! truc x nhu cu tu trai qua
                            50.0f, 70.0f, 
                            Color.blue,
                            50, -10,
                            Color.red);             
    g2D.setPaint(gp);
    g2D.drawString(str, 50,50);                                                 
  }
}