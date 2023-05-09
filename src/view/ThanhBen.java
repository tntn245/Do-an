/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author My PC
 */
public class ThanhBen {
    public JPanelGradient pane_gradient;
    public JButton btn_NhanVien;
    public JButton btn_KhachHang;
    public JButton btn_HoaDon;
    public JButton btn_ThucDon;
    public JButton btn_Kho;

    public ThanhBen(){
        init();
    }
    
    public void init(){
        
        pane_gradient = new JPanelGradient(167,223,255, 255, 184, 183);
        pane_gradient.setPreferredSize(new Dimension(100, 600));        
        btn_NhanVien();
        btn_KhachHang();
        btn_HoaDon();
        btn_ThucDon();
        btn_Kho();
    }
    
    public void pane_background(){
        
    }

    public void btn_NhanVien(){    
        btn_NhanVien = new JButton ("NHÂN VIÊN");
        btn_NhanVien.setOpaque(false);
        btn_NhanVien.setContentAreaFilled(false);
        btn_NhanVien.setFocusPainted(false);
        btn_NhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font newButtonFont=new Font(btn_NhanVien.getFont().getName(),Font.BOLD,btn_NhanVien.getFont().getSize());
        btn_NhanVien.setFont(newButtonFont);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/employee.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_NhanVien.setIcon(newIcon);
        
        btn_NhanVien.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn_NhanVien.setHorizontalTextPosition(SwingConstants.CENTER);
        
        pane_gradient.add(btn_NhanVien);
    }
    
    public void btn_KhachHang(){
        btn_KhachHang = new JButton ("KHÁCH HÀNG");
        btn_KhachHang.setOpaque(false);
        btn_KhachHang.setContentAreaFilled(false);
        btn_KhachHang.setFocusPainted(false);
        btn_KhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font newButtonFont=new Font(btn_KhachHang.getFont().getName(),Font.BOLD,btn_KhachHang.getFont().getSize());
        btn_KhachHang.setFont(newButtonFont);

        ImageIcon icon = new ImageIcon(getClass().getResource("/image/buyer.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_KhachHang.setIcon(newIcon);
        
        btn_KhachHang.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn_KhachHang.setHorizontalTextPosition(SwingConstants.CENTER);
        
        pane_gradient.add(btn_KhachHang);
    }

    public void btn_HoaDon(){
        btn_HoaDon = new JButton ("HÓA ĐƠN");
        btn_HoaDon.setOpaque(false);
        btn_HoaDon.setContentAreaFilled(false);
        btn_HoaDon.setFocusPainted(false);
        btn_HoaDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font newButtonFont=new Font(btn_HoaDon.getFont().getName(),Font.BOLD,btn_HoaDon.getFont().getSize());
        btn_HoaDon.setFont(newButtonFont);

        ImageIcon icon = new ImageIcon(getClass().getResource("/image/bill.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_HoaDon.setIcon(newIcon);
        
        btn_HoaDon.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn_HoaDon.setHorizontalTextPosition(SwingConstants.CENTER);
        
        pane_gradient.add(btn_HoaDon);
    }
    
    public void btn_ThucDon(){
        btn_ThucDon = new JButton ("THỰC ĐƠN");
        btn_ThucDon.setOpaque(false);
        btn_ThucDon.setContentAreaFilled(false);
        btn_ThucDon.setFocusPainted(false);
        btn_ThucDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font newButtonFont=new Font(btn_ThucDon.getFont().getName(),Font.BOLD,btn_ThucDon.getFont().getSize());
        btn_ThucDon.setFont(newButtonFont);

        ImageIcon icon = new ImageIcon(getClass().getResource("/image/menu.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_ThucDon.setIcon(newIcon);
        
        btn_ThucDon.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn_ThucDon.setHorizontalTextPosition(SwingConstants.CENTER);
        
        pane_gradient.add(btn_ThucDon);
    }
    

    public void btn_Kho(){
        btn_Kho = new JButton ("KHO");
        btn_Kho.setOpaque(false);
        btn_Kho.setContentAreaFilled(false);
        btn_Kho.setFocusPainted(false);
        btn_Kho.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font newButtonFont=new Font(btn_Kho.getFont().getName(),Font.BOLD,btn_Kho.getFont().getSize());
        btn_Kho.setFont(newButtonFont);

        ImageIcon icon = new ImageIcon(getClass().getResource("/image/cardboard.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_Kho.setIcon(newIcon);
        
        btn_Kho.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn_Kho.setHorizontalTextPosition(SwingConstants.CENTER);
        
        pane_gradient.add(btn_Kho);
    }
}
