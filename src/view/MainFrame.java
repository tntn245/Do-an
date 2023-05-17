/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import quanlynhahang.Connect;
import quanlynhahang.QuanLyNhaHang;

/**
 *
 * @author My PC
 */
public class MainFrame extends JFrame {
    private Connection connection = null;
    private Statement statement;
    private String sql;

    private DangNhap dangnhap;
    private DangKyKH dangky;
        
    private JLayeredPane pane_background_DangNhap;
    private JPanel pane_content_DangNhap;
    private JLayeredPane pane_DangKy;
    
    private ThanhBen thanhben;
    
    private TrangChuQL trangchuQL;
    JLayeredPane pane_TrangChuQL;
    
    private QuanlyNhanVien QLNV;
    JPanel pane_QLNV;
    
    private QuanlyKho QLKho;
    JPanel pane_QLKho;
    
    private PhieuNhap PhieuNhap;
    JPanel pane_PhieuNhap;

    private PhieuXuat PhieuXuat;
    JPanel pane_PhieuXuat;
    
    public MainFrame() throws IOException, SQLException{
        init();        
        setVisible(true);
        setResizable(false);
    }
    
    public void init() throws IOException, SQLException{
        this.setTitle("Nhà hàng Haru");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(230, 241, 247));
        this.setLayout(new BorderLayout());
        ConnectDB();
        init_attributes();
        Flow();
    }
    
    public void init_attributes() throws IOException{
        dangnhap = new DangNhap();
        pane_background_DangNhap = dangnhap.pane_background();
        pane_content_DangNhap = dangnhap.pane_login();
        
        trangchuQL = new TrangChuQL();
        pane_TrangChuQL = trangchuQL.pane_layer;
        
        thanhben = new ThanhBen();
        
        QLNV = new QuanlyNhanVien(connection);
        pane_QLNV = QLNV.pane_QLNV();
        
        QLKho = new QuanlyKho(connection);
        pane_QLKho = QLKho.pane_QLKho();
        
        PhieuNhap = new PhieuNhap(connection);
        pane_PhieuNhap = PhieuNhap.pane_PhieuNhap();
        
        PhieuXuat = new PhieuXuat(connection);
        pane_PhieuXuat = PhieuXuat.pane_PhieuXuat();
    }
    
    public void ConnectDB() throws SQLException{
        Connect connect = new Connect();
        connection = connect.LinkConnection("jdbc:oracle:thin:@localhost:1521:orcl", "DOAN", "doan");
        statement = connection.createStatement();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Sucess loading driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }
        
    }
    
    public void Login(){
        dangnhap.btn_DangNhap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String username = dangnhap.TenTK_Text.getText();
                String password = dangnhap.MatKhau_Text.getText();
                
                if (username.equals("") || password.equals("")){
                    dangnhap.ThieuThongTin_jOptionPane.setVisible(true);
                    dangnhap.ThieuThongTin_jOptionPane.showMessageDialog(pane_content_DangNhap, "Vui lòng nhập đầy đủ thông tin!");
                    dangnhap.ThieuThongTin_jOptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                try {
                    sql = "SELECT * FROM TAIKHOAN";
                    ResultSet res = statement.executeQuery(sql);
                    
                    boolean flag = false;
                    while(res.next()){
                        String taikhoan = res.getString("TENTK");
                        String matkhau = res.getString("MATKHAU");
                        String loai = res.getString("LOAI");
                        
                        if(username.equals(taikhoan) && password.equals(matkhau)){
                            System.out.println(loai);
                            pane_background_DangNhap.setVisible(false);
                            pane_content_DangNhap.setVisible(false);  
                            add(thanhben.pane_gradient, BorderLayout.WEST);
                            add(pane_TrangChuQL,BorderLayout.CENTER);
                            flag=true;
                            break;
                        }                        
                        else {
                            System.out.println("Tai khoan/ Mat khau khong dung.");
                        }
                    }
                    if(!flag){
                        dangnhap.SaiTK_jOptionPane.setVisible(true);
                        dangnhap.SaiTK_jOptionPane.showMessageDialog(pane_content_DangNhap, "Tên tài khoản/ Mật khẩu không đúng!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                 
    }
    
    public void QLNV(){
        thanhben.btn_NhanVien.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam QLNV");
                if(pane_TrangChuQL.isDisplayable())
                    pane_TrangChuQL.setVisible(false);
                if(pane_QLKho.isDisplayable()){
                    pane_QLKho.setVisible(false);
                    pane_QLNV.setVisible(true);
                }
                if(pane_PhieuNhap.isDisplayable()){
                    pane_PhieuNhap.setVisible(false);
                    pane_QLNV.setVisible(true);
                }  
                if(pane_PhieuXuat.isDisplayable()){
                    pane_PhieuXuat.setVisible(false);
                    pane_QLNV.setVisible(true);
                } 
                add(pane_QLNV,BorderLayout.CENTER);
            }
        });
    }

    public void QLKho(){
        thanhben.btn_Kho.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam QLKho");
                if(pane_TrangChuQL.isDisplayable())
                    pane_TrangChuQL.setVisible(false);
                if(pane_QLNV.isDisplayable()){
                    pane_QLNV.setVisible(false);
                    pane_QLKho.setVisible(true);
                }
                if(pane_PhieuNhap.isDisplayable()){
                    pane_PhieuNhap.setVisible(false);
                    pane_QLKho.setVisible(true);
                }          
                if(pane_PhieuXuat.isDisplayable()){
                    pane_PhieuXuat.setVisible(false);
                    pane_QLKho.setVisible(true);
                }      
                add(pane_QLKho,BorderLayout.CENTER);
            }
        });

        QLKho.btn_PhieuNhap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam phieu nhap");
                if(pane_QLKho.isDisplayable()){
                    pane_QLKho.setVisible(false);
                    pane_PhieuNhap.setVisible(true);
                }
                if(pane_PhieuXuat.isDisplayable()){
                    pane_PhieuXuat.setVisible(false);
                    pane_PhieuNhap.setVisible(true);
                }
                add(pane_PhieuNhap, BorderLayout.CENTER);
            }
        });
        
        QLKho.btn_PhieuXuat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam phieu xuat");
                if(pane_QLKho.isDisplayable()){
                    pane_QLKho.setVisible(false);
                    pane_PhieuXuat.setVisible(true);
                }
                if(pane_PhieuNhap.isDisplayable()){
                    pane_PhieuNhap.setVisible(false);
                    pane_PhieuXuat.setVisible(true);
                }
                add(pane_PhieuXuat, BorderLayout.CENTER);
            }
        });
    }    

    public void QLPhieuNhap(){
        PhieuNhap.btn_Kho.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam kho");
                if(pane_PhieuNhap.isDisplayable()){
                    pane_PhieuNhap.setVisible(false);
                    pane_QLKho.setVisible(true);
                }
                add(pane_QLKho, BorderLayout.CENTER);
            }
        });
        
        PhieuNhap.btn_PhieuXuat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam phieu xuat");
                if(pane_PhieuNhap.isDisplayable()){
                    pane_PhieuNhap.setVisible(false);
                    pane_PhieuXuat.setVisible(true);
                }
                add(pane_PhieuXuat, BorderLayout.CENTER);
            }
        });
        
        PhieuNhap.btn_XacNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                QLKho.add_data_table();
            }
        });
    }        
    
    public void QLPhieuXuat(){
        PhieuXuat.btn_Kho.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam kho");
                if(pane_PhieuXuat.isDisplayable()){
                    pane_PhieuXuat.setVisible(false);
                    pane_QLKho.setVisible(true);
                }
                add(pane_QLKho, BorderLayout.CENTER);
            }
        });
        
        PhieuXuat.btn_PhieuNhap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam phieu nhap");
                if(pane_PhieuXuat.isDisplayable()){
                    pane_PhieuXuat.setVisible(false);
                    pane_PhieuNhap.setVisible(true);
                }
                add(pane_PhieuNhap, BorderLayout.CENTER);
            }
        });
    }        
    public void Choose_Regist(){
        dangnhap.DangKy.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    pane_background_DangNhap.setVisible(false);
                    pane_content_DangNhap.setVisible(false);  
                    pane_DangKy.setVisible(true);
                    add(pane_DangKy, BorderLayout.CENTER); // nếu chưa add
                }                 
                catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dangnhap.DangKy.setForeground(new Color(46,174,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dangnhap.DangKy.setForeground(Color.black);
            }

        });
    }
    
    public void Register(){
        dangky.btn_DangKy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pane_DangKy.setVisible(false);
                pane_background_DangNhap.setVisible(true);
                pane_content_DangNhap.setVisible(true); 
            }
        });
    }
    
    public void Return_Login(){
        dangky.btn_TroVe.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pane_DangKy.setVisible(false);
                pane_background_DangNhap.setVisible(true);
                pane_content_DangNhap.setVisible(true);  
            }
        });
    }
    
    public void Flow() throws IOException, SQLException{
        dangky = new DangKyKH(connection);
        pane_DangKy = dangky.set_pane();
        add(pane_background_DangNhap, BorderLayout.CENTER);
        add(pane_content_DangNhap, BorderLayout.EAST);
        
        Login();
        Choose_Regist();
//        Register();
        Return_Login();
        QLNV();
        QLKho();
        QLPhieuNhap();
        QLPhieuXuat();
    }
}



//    private String GioiTinh_DangKy;
//    public void Regist(){
//        dangky.Nam.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (dangky.Nam.isSelected()) {
//                    GioiTinh_DangKy="Nam";
//                }
//            }
//        });
//        dangky.Nu.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                if (dangky.Nu.isSelected()) {
//                    GioiTinh_DangKy="Nu";
//                }
//            }
//        });
//        
//        dangky.btn_DangKy.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                String HoTen = dangky.HoTen_Text.getText();
//                String SDT = dangky.SDT_Text.getText();
//                String DiaChi = dangky.DiaChi_Text.getText();
//                String NgSinh = dangky.NgSinh_Text.getText();
////                String GioiTinh;
//                String TenTK = dangky.TaiKhoan_Text.getText();
//                String MatKhau = dangky.MatKhau_Text.getText();
//                String NhapLaiMK = dangky.Nhap_Lai_MatKhau_Text.getText();
//                String LoaiTK = "Khach hang";
//
//                try {
//                    sql = "INSERT INTO TAIKHOAN VALUES (  '' , '" + TenTK + "', '" + MatKhau + "' , '" + LoaiTK + "' )";
//                    int res = statement.executeUpdate(sql);
//                    System.out.println("Insert thanh cong");
//                    sql = "INSERT INTO KHACHHANG VALUES (  '' , '" + HoTen + "', '" + SDT + "' , '" + DiaChi + "' , '" + NgSinh +"' , '" + GioiTinh_DangKy + "', '' )";
//                    res = statement.executeUpdate(sql);
//                    System.out.println("Insert thanh cong");
//                } catch (SQLException ex) {
//                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }