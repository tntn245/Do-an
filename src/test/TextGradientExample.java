/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author My PC
 */
public class TextGradientExample extends JPanel {
    private String str;
    public TextGradientExample(String str){
        this.str = str;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Inder", Font.BOLD, 50);
        g2d.setFont(font);
        int stringWidth = g2d.getFontMetrics().stringWidth(str);
        int stringHeight = g2d.getFontMetrics().getHeight();
        int x = (getWidth() - stringWidth) / 2;
        int y = getHeight() / 2;
        GradientPaint gradient = new GradientPaint(x, y - stringHeight/2, new Color(167,223,255), x, y , new Color( 255, 184, 183));
        g2d.setPaint(gradient);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawString(str, x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Gradient Example");
        TextGradientExample textgra = new TextGradientExample("ĐĂNG NHẬP");
        textgra.setBackground(Color.white);
        textgra.setPreferredSize(new Dimension(350, 100));
        JPanel a = new JPanel();
        
        frame.setLayout(new FlowLayout());
        frame.add(textgra);        
        frame.add(a);

        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}