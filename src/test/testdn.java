/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import view.ButtonGradient;
import view.ButtonGradient;
import view.JPanelGradient;
import view.JPanelGradient;

/**
 *
 * @author My PC
 */
public class testdn extends JFrame {
    private JLayeredPane pane_layer;
    private JPanel pane_gradient;
    private JLabel jLabel;
    
    private JPanel pane_login;
    private JPanel pane_DangNhap;
    private JPanel pane_account;
    private GridBagConstraints gbc;
    
    public testdn() throws IOException{
        init();        
        setVisible(true);
        setResizable(false);
    }
    
    public void init() throws IOException{
        this.setTitle("Test");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(230, 241, 247));
        this.setLayout(new BorderLayout());
        
        pane_gradient();
        pane_img(); // chi goi duoc o init moi chay ra :D ???              
        pane_background();
        pane_login();
    }
    
    public void pane_gradient(){
        pane_gradient = new JPanelGradient(167,223,255, 255, 184, 183);
        pane_gradient.setPreferredSize(new Dimension(350, getHeight()));
    }
    
    public void pane_img() throws IOException{ 
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\My PC\\Downloads\\—Pngtree—japan fuji mountain landscape illustration_4622958 - Cut.png"));
        Image images = bufferedImage.getScaledInstance(357, 534, Image.SCALE_DEFAULT); //SCALE_SMOOTH
        ImageIcon icon = new ImageIcon(images);
        jLabel = new JLabel();
        jLabel.setIcon(icon);
    }
    
    public void pane_background(){
        pane_layer = new JLayeredPane();
        jLabel.setBounds(0, 0, 350, getHeight());
        pane_gradient.setBounds(0, 0, 350, getHeight());
        pane_layer.add(pane_gradient, new Integer(1));
        pane_layer.add(jLabel, new Integer(2));
        this.add(pane_layer, BorderLayout.CENTER);
    }
    
    public void pane_login() throws IOException{
        pane_login = new JPanel();
        pane_login.setPreferredSize( new Dimension( 550, getHeight() ) );
        pane_login.setBackground(Color.white);
        pane_login.setLayout(new FlowLayout());
        account();
        this.add(pane_login, BorderLayout.EAST);
    }
    
    public void pane_DangNhap(){
        pane_DangNhap = new JPanel();
//        pane_DangNhap.setPreferredSize( new Dimension( 550, 80));
        pane_DangNhap.setBackground(Color.white);
        pane_DangNhap.setBorder(new EmptyBorder(60, 0, 00, 0));
        JLabel DangNhap = new JLabel("ĐĂNG NHẬP");
        DangNhap.setFont(new Font("SansSerif", Font.BOLD, 28));
        pane_DangNhap.add(DangNhap);
        pane_login.add(pane_DangNhap);
    }
    
    public void init_pane_account(){
        pane_account = new JPanel();
        pane_account.setPreferredSize( new Dimension( 550, getHeight()/2 ) );
        pane_account.setBackground(Color.white);        
        pane_account.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();  
    }
    
    public void account() throws IOException{
        pane_DangNhap();
        init_pane_account();
        
        //Create new Border for Text field
        Border newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.ipady = 5;
        JLabel TenTK =new JLabel("Tên tài khoản:");
        TenTK.setFont(new Font("SansSerif", Font.PLAIN, 16));
        TenTK.setForeground(Color.black);
        pane_account.add(TenTK, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;        
        gbc.gridwidth = 2;
//        JTextField TenTK_Text = new JTextField(30);
//        TenTK_Text.setSize(50,20);
//        TenTK_Text.setFont(new Font("SansSerif", Font.PLAIN, 16));
////        TenTK_Text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//        TenTK_Text.setBorder(newBorder);
        pane_account.add(pane_TenTK_Text(), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel MatKhau =new JLabel("Mật khẩu:");
        MatKhau.setBorder(new EmptyBorder(10, 0, 0,0));
        MatKhau.setFont(new Font("SansSerif", Font.PLAIN, 16));
        MatKhau.setForeground(Color.black);
        pane_account.add(MatKhau, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;        
        gbc.gridwidth = 2;
        JTextField MatKhau_Text = new JTextField(30);
        MatKhau_Text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        MatKhau_Text.setSize(50, 20);
        MatKhau_Text.setBorder(newBorder);
        pane_account.add(MatKhau_Text, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        JLabel forget = new JLabel("Quên mật khẩu ?");
        forget.setBorder(new EmptyBorder(10, 0, 0,0));
        forget.setFont(new Font("SansSerif", Font.PLAIN, 16));
        forget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel pane_forget = new JPanel();
        pane_forget.setLayout( new FlowLayout(FlowLayout.RIGHT) );
        pane_forget.setBackground(Color.white);
        pane_forget.add(forget);
        pane_account.add(pane_forget, gbc);
        
        
        pane_login.add(pane_account);
        btn_DangNhap();
        DangKy();
    }

    
    //Chen icon
    public JLayeredPane pane_TenTK_Text() throws IOException{
        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(100, 50));

        JTextField TenTK_Text = new JTextField(30);
        TenTK_Text.setSize(50,20);
        TenTK_Text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        Border newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
        TenTK_Text.setBorder(newBorder);
        
        JLabel label_icon = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/Github-Octicons-Person-16.512.png"));
        Image img = icon.getImage();
        Image image = img.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
        ImageIcon newIcon = new ImageIcon(image);   
        label_icon = new JLabel();
        label_icon.setIcon(newIcon);       
        
//        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\My PC\\Downloads\\Picture4.png"));
//        Image images = bufferedImage.getScaledInstance(17, 17, Image.SCALE_DEFAULT); //SCALE_SMOOTH
//        ImageIcon i = new ImageIcon(images);
//        label_icon = new JLabel();
//        label_icon.setIcon(i);
        
        TenTK_Text.setBounds(0, 0, 1000, 20);
        label_icon.setBounds(360, -17, 50, 50);
        layer.add(TenTK_Text, new Integer(1));
        layer.add(label_icon, new Integer(2));
        return layer;
    }
    
    

    public JLayeredPane pane_MatKhau_Text() throws IOException{
        JLayeredPane layer = new JLayeredPane();
        layer.setPreferredSize(new Dimension(100, 20));

        JTextField MatKhau_Text = new JTextField(30);
        MatKhau_Text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        MatKhau_Text.setSize(50, 20);
        Border newBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
        MatKhau_Text.setBorder(newBorder);
        
        JLabel label_icon = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/Github-Octicons-Key-16.512.png"));
        Image img = icon.getImage();
        Image image = img.getScaledInstance(117/3, 103/3,  java.awt.Image.SCALE_DEFAULT);
        ImageIcon newIcon = new ImageIcon(image);   
        label_icon = new JLabel();
        label_icon.setIcon(newIcon);       
        
        MatKhau_Text.setBounds(0, 0, 1000, 20);
        label_icon.setBounds(360, -17, 50, 20);
        layer.add(MatKhau_Text, new Integer(1));
        layer.add(label_icon, new Integer(2));
        return layer;
    }            

    public void btn_DangNhap(){
        JPanel pane_btn_DangNhap = new JPanel();
        pane_btn_DangNhap.setPreferredSize( new Dimension( 550, 50 ) );
        pane_btn_DangNhap.setBackground(Color.white);

        ButtonGradient btn_DangNhap = new ButtonGradient();
        btn_DangNhap.setText("ĐĂNG NHẬP");
        btn_DangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_DangNhap.setFont(new Font("SansSerif", Font.PLAIN, 20));
//        btn_DangNhap.setBorder(new EmptyBorder(10, 10, 10,10));
        btn_DangNhap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pane_DangNhap.setVisible(false);
            }
        });
        pane_btn_DangNhap.add(btn_DangNhap);
        pane_login.add(pane_btn_DangNhap);


//        btn_DangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btn_DangNhap.setFont(new Font("SansSerif", Font.PLAIN, 20));
//        btn_DangNhap.setBackground(Color.red);
////        btn_DangNhap.setBorder(new EmptyBorder(10, 10, 10,10));
//        btn_DangNhap.setBorder(BorderFactory.createCompoundBorder(
//               BorderFactory.createLineBorder(Color.black, 1),
//               BorderFactory.createLineBorder(Color.red, 15)));
//        pane_login.add(btn_DangNhap);
    }
    
    public void DangKy(){
        JLabel Text =new JLabel("Đăng ký tài khoản mới ");
        Text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JLabel DangKy =new JLabel("Bấm vào đây");
        DangKy.setFont(new Font("SansSerif", Font.BOLD, 16));
        DangKy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DangKy.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                pane_DangNhap.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                DangKy.setForeground(new Color(46,174,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                DangKy.setForeground(Color.black);
            }

        });
        JPanel pane_DangKy = new JPanel();
        pane_DangKy.setBackground(Color.white);
        pane_DangKy.setBorder(new EmptyBorder(20, 0, 0,0));
        pane_DangKy.add(Text);
        pane_DangKy.add(DangKy);
        pane_login.add(pane_DangKy);
    }
}
