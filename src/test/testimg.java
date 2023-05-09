/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.ImagePanel;
import view.ImagePanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author My PC
 */
public class testimg extends JFrame {
    public testimg(){
        this.init();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void init(){
        this.setTitle("Test");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(230, 241, 247));
        
        imgpane();
    }
    public void imgpane(){
        ImageIcon i = new ImageIcon(getClass().getResource("/image/o.png"));
        Image im = i.getImage();
        Image ima = im.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImagePanel ip = new ImagePanel (im);
        add(ip, BorderLayout.CENTER);
    }
    
}

