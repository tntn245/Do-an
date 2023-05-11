/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author My PC
 */
public class DropShadowJPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public int pixels;
    public int r;
    public int g;
    public int b;
    public Color color_bg;

    public DropShadowJPanel(int pix, int r, int g, int b, Color color_bg) {
        this.pixels = pix;
        this.r = r;
        this.g = g;
        this.b = b;
        this.color_bg = color_bg;
//        Border border = BorderFactory.createLineBorder(new Color(255,255,255,1), pixels);
        Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
        this.setBorder(BorderFactory.createCompoundBorder(  border, this.getBorder()));
        this.setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int topOpacity = 100;
        
        Insets insets = getBorder().getBorderInsets(this);
        g.setColor(color_bg);
        g.fillRect(insets.left, insets.top,
        getWidth() - insets.left - insets.right,
        getHeight() - insets.top - insets.bottom);

        for (int i = 0; i < pixels; i++) {
            g.setColor(new Color(this.r, this.g, this.b, ((topOpacity / pixels) * i)));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
    }
    
    public static void main(String args[]){
        JFrame frame = new JFrame("Drop shadow Example");
        
        DropShadowJPanel pane = new DropShadowJPanel(5, 0, 0, 0, Color.white );
//        pane.setBackground(Color.red);
//        pane.setBorder(BorderFactory.createLineBorder(Color.red));
        pane.setPreferredSize(new Dimension(400, 400));
        
        JPanel p = new JPanel();
//        p.setBackground(Color.gray);
        p.setBorder(new FlatDropShadowBorder());
//        p.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(0,0,0, 50), 10), BorderFactory.createLineBorder(Color.red)));
//        p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        p.setSize(100, 100);
        p.setPreferredSize(new Dimension(500, 500));
        
//        pane.add(p); 
        
        frame.setLayout(new FlowLayout());     
        frame.add(pane);
        frame.add(p);

        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(230,241,247) );
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}