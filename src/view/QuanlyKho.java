/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author My PC
 */
public class QuanlyKho {
    private JPanel pane_bg;
    
    private JPanel pane_QLKho;
    private DropShadowPane pane_shadow;
    private JTable table_Kho;
    private JScrollPane Scrollpane_TableKho;
    
    public ButtonGradient btn_PhieuNhap;
    public ButtonGradient btn_PhieuXuat;
    public ButtonGradient btn_ThemNL;
    
    public JPanel pane_Search;
    public JButton btn_Search;
    public JComboBox boxSearch;
    public JTextField txtSearch;
    
    public JDialog formNL_jDialog;
    public JPanel pane_bg_ThemNL;
//    public JPanel pane_ThemNL;
//    public JOptionPane themNL_jOptionPane = new JOptionPane();
//    public JOptionPane suaNL_jOptionPane = new JOptionPane();
//    public JOptionPane confirm_xoaNL_jOptionPane = new JOptionPane();
    
//    private JTextField txt_IDNhanVien;
//    private JTextField txt_HoTen;
//    private JTextField txt_CCCD;
//    private PrefixTextField txt_SDT;
//    private JComboBox txt_GioiTinh;
//    private JTextField txt_Email;
//    private JTextField txt_NgaySinh;
//    private JTextField txt_ChucVu;
//    private JTextField txt_NgayVaoLam;
//    private JFormattedTextField txt_Luong;
//    private JTextField txt_DiaChi;
//
//    private JOptionPane ThieuThongTin_jOptionPane = new JOptionPane();
//    private JOptionPane CCCDTontai_jOptionPane = new JOptionPane();
    private JOptionPane Delete_Confirm_jOptionPane = new JOptionPane();
    
    private Connection connection;
    
    public QuanlyKho(Connection connection){
        this.connection = connection;
        init_pane();
    }
    
    public void init_pane(){
        pane_bg = new JPanel();
        pane_bg.setOpaque(true);
        pane_bg.setBackground(new Color(230, 235, 240));

        pane_bg.setPreferredSize(new Dimension(800, 600)); 
        
        pane_shadow = new DropShadowPane(4, 0, 0, 0, Color.white, 50);
        pane_shadow.setPreferredSize(new Dimension(770, 550)); 
        
        pane_QLKho = new JPanel();
        pane_QLKho.setBackground(Color.white);
        pane_QLKho.setPreferredSize(new Dimension(760, 540)); 
    }    
    public JPanel pane_QLKho(){
        search_bar();
        table_NL();
        btn_ThemNL();
        pane_shadow.add(pane_QLKho);
        pane_bg.add(pane_shadow);
        return pane_bg;
    }
    
    public void search_bar(){
        pane_Search = new JPanel();
        pane_Search.setBackground(Color.white);
        pane_Search.setPreferredSize(new Dimension(800, 70)); 
        pane_search();
        pane_ThemNL();
        
        btn_PhieuNhap();
        btn_PhieuXuat();
        
        pane_QLKho.add(pane_Search);
    }
    
    public void pane_search(){
        JPanel pane_search_bar = new JPanel();
        pane_search_bar.setBackground(Color.white );
        pane_search_bar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        txtSearch = new JTextField(" Search");
        txtSearch.setPreferredSize(new Dimension(50, 31)); 
        txtSearch.setColumns(15);     
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSearch.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                txtSearch.setText("");
                txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
                txtSearch.setForeground(Color.BLACK);
            }
            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        
        btn_Search = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/search1.png"));
        Image image = icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_Search.setIcon(newIcon);
        btn_Search.setBackground(Color.white);
        btn_Search.setPreferredSize(new Dimension(40, 31)); 
        btn_Search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pane_search_bar.add(txtSearch);
        pane_search_bar.add(btn_Search);
        
        String colname_NL[] = {"MANL", "TENNL", "DONGIA", "DONVI", "TONGSL"};
        boxSearch = new JComboBox(colname_NL);
        boxSearch.setSelectedItem("TENNL");
        boxSearch.setFont(new Font("SansSerif", Font.BOLD, 14));
        boxSearch.setBackground(Color.white);
        boxSearch.setPreferredSize(new Dimension(135, 35)); 

        Search();
        
        pane_search_bar.setBorder(txtSearch.getBorder());
        txtSearch.setBorder(null);
        btn_Search.setBorder(null);
        
        pane_Search.add(pane_search_bar);
        pane_Search.add(boxSearch);
        pane_Search.setBorder(new EmptyBorder(10, 0, 0, 0));
    }
    
    public void pane_ThemNL(){
        pane_bg_ThemNL = new JPanel();
        pane_bg_ThemNL.setBackground(new Color(0,0,0,50));
        pane_bg_ThemNL.setPreferredSize(new Dimension(769, 550));
        pane_bg_ThemNL.setBounds(8, 7, 769, 550);
        
        pane_bg_ThemNL = new JPanel();
        pane_bg_ThemNL.setBackground(Color.white);
        pane_bg_ThemNL.setPreferredSize(new Dimension(600, 500));
        pane_bg_ThemNL.setBounds(70, 30, 600, 500);
    }
    
    public void Search(){
        boxSearch.addActionListener(new ActionListener() {     
            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("Value: " + boxSearch.getSelectedItem().toString());      
            }
        });
        btn_Search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam tim kiem NL");
                try{
                    String sql = "SELECT * FROM KHONGUYENLIEU WHERE "+ boxSearch.getSelectedItem().toString() + " LIKE '%" + txtSearch.getText() + "%'";
                    Statement statement = connection.createStatement();
                    ResultSet res = statement.executeQuery(sql);
                    
                    DefaultTableModel tbmodel = (DefaultTableModel)table_Kho.getModel();
                    tbmodel.setRowCount(0);

                    while(res.next()){
                        String MANL = res.getString("MANL");
                        String TENNL = res.getString("TENNL");
                        String DONGIA = res.getString("DONGIA");
                        String DONVI = res.getString("DONVI");
                        String TONGSL = res.getString("TONGSL");

                        Object tbdata[] = {MANL, TENNL, DONGIA, DONVI, TONGSL, null};
                        tbmodel.addRow(tbdata);
                    }
                }
                catch(SQLException | HeadlessException ex){
                            System.out.println("the error is"+ex);
                }
                
            }
        });
    }

    public void init_Dialog(){
        formNL_jDialog = new JDialog();
        formNL_jDialog.getContentPane().setBackground(new Color(255, 255, 255));
        formNL_jDialog.setMinimumSize(new Dimension(700, 500));
        formNL_jDialog.setModal(true);
        formNL_jDialog.setResizable(false);
        GridBagLayout jDialogLayout = new GridBagLayout();
        jDialogLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jDialogLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        formNL_jDialog.getContentPane().setLayout(jDialogLayout);
    }
    
//    public void Dialog_form( boolean loaiDialog, int row){
//        boolean loai = loaiDialog;
//        init_Dialog();
//        
//        GridBagConstraints gbc = new GridBagConstraints();
//        
//        JLabel IDNhanVien = new JLabel("ID Nhân viên:");
//        JLabel HoTen = new JLabel("Họ tên:");
//        JLabel CCCD = new JLabel("CMND/CCCD:");
//        JLabel SDT = new JLabel("Số điện thoại:");
//        JLabel GioiTinh = new JLabel("Giới tính");
//        JLabel Email = new JLabel("E-mail:");
//        JLabel NgaySinh = new JLabel("Ngày sinh:");
//        JLabel ChucVu = new JLabel("Chức vụ:");
//        JLabel NgayVaoLam = new JLabel("Ngày vào làm:");
//        JLabel Luong = new JLabel("Lương:");
//        JLabel DiaChi = new JLabel("Địa chỉ:");
//        
//        NumberFormat format = NumberFormat.getInstance();
//        NumberFormatter formatter = new NumberFormatter(format);
//        formatter.setValueClass(Integer.class);
//        formatter.setMinimum(0);
//        formatter.setMaximum(Integer.MAX_VALUE);
//        formatter.setAllowsInvalid(false);
//        formatter.setCommitsOnValidEdit(true);
//        
//        txt_IDNhanVien = new JTextField();
//        txt_HoTen = new JTextField();
//        
//        txt_CCCD = new JTextField();
//        txt_CCCD.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                Number_keyPressed(e, txt_CCCD, 12);
//            };
//        });
//        
//        txt_SDT = new PrefixTextField("0");
//        txt_SDT.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                Number_keyPressed(e, txt_SDT, 10);
//            };
//        });
//
//        String StrGioiTinh[] = {"Nam", "Nữ"};
//        txt_GioiTinh = new JComboBox(StrGioiTinh);
//        txt_Email = new JTextField();
//        txt_NgaySinh = new JTextField();
//        DateChooser ngaySinh_dateChooser = new DateChooser();
//        ngaySinh_dateChooser.setForeground(new java.awt.Color(167, 223, 255));
//        ngaySinh_dateChooser.setTextRefernce(txt_NgaySinh);
//        txt_ChucVu = new JTextField();
//        txt_NgayVaoLam = new JTextField();
//        DateChooser ngayVaoLam_dateChooser = new DateChooser();
//        ngayVaoLam_dateChooser.setForeground(new java.awt.Color(255, 184, 183));
//        ngayVaoLam_dateChooser.setTextRefernce(txt_NgayVaoLam);
//        txt_Luong = new JFormattedTextField(formatter);
//        txt_DiaChi = new JTextField();
//        
//        JButton btn_cancel_themNV = new JButton("HỦY");
//        btn_cancel_themNV.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                Exit_Dialog_jButtonActionPerformed(evt);
//            }
//        });
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 68;
//        gbc.gridy = 18;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(btn_cancel_themNV, gbc);
//        
//        JButton btn_XacNhan = new JButton("XÁC NHẬN");
//        btn_XacNhan.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                if(loai)
//                    themNV_jButtonActionPerformed(evt);
//                else 
//                    suaNV_jButtonActionPerformed(evt, row);
//            }
//        });
//        gbc = new GridBagConstraints();
//        gbc.gridx = 62;
//        gbc.gridy = 18;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(btn_XacNhan, gbc);
//        
//        
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(IDNhanVien, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 56;
//        gbc.gridy = 0;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(HoTen, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 0;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(CCCD, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 4;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(SDT, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 4;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(GioiTinh, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 56;
//        gbc.gridy = 4;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(Email, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 8;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(NgaySinh, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 56;
//        gbc.gridy = 8;
//        gbc.gridwidth = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(ChucVu, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 8;
//        gbc.gridwidth = 5;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(NgayVaoLam, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 12;
//        gbc.gridwidth = 5;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(Luong, gbc);
//
//        gbc = new java.awt.GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 12;
//        gbc.gridwidth = 5;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        formNV_jDialog.getContentPane().add(DiaChi, gbc);
//        
//        if(loai)
//            setText_nextMANV();
//        else
//            setText_currMANV(row);
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 23;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_IDNhanVien, gbc);
//
//        txt_HoTen.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
////                hoTen_jTextFieldActionPerformed(evt);
//            }
//        });
//        gbc = new GridBagConstraints();
//        gbc.gridx = 56;
//        gbc.gridy = 2;
//        gbc.gridwidth = 19;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_HoTen, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 2;
//        gbc.gridwidth = 25;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_CCCD, gbc);
//        
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 6;
//        gbc.gridwidth = 23;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_SDT, gbc);
//        
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 6;
//        gbc.gridwidth = 25;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_GioiTinh, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 56;
//        gbc.gridy = 6;
//        gbc.gridwidth = 19;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_Email, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 10;
//        gbc.gridwidth = 23;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_NgaySinh, gbc);
//
//        txt_ChucVu.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
////                chucVu_jTextFieldActionPerformed(evt);
//            }
//        });
//        
//        gbc = new GridBagConstraints();
//        gbc.gridx = 56;
//        gbc.gridy = 10;
//        gbc.gridwidth = 19;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_ChucVu, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 10;
//        gbc.gridwidth = 25;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_NgayVaoLam, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 14;
//        gbc.gridwidth = 23;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_Luong, gbc);
//
//        gbc = new GridBagConstraints();
//        gbc.gridx = 26;
//        gbc.gridy = 14;
//        gbc.gridwidth = 49;
//        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
//        formNV_jDialog.getContentPane().add(txt_DiaChi, gbc);    
//    }
    
    
    public void btn_PhieuNhap(){
        btn_PhieuNhap = new ButtonGradient();
        btn_PhieuNhap.setColor1(new Color(255,231,231));
        btn_PhieuNhap.setColor2(new Color(255,130,145));
        
        btn_PhieuNhap.setText("PHIẾU NHẬP");
        btn_PhieuNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_PhieuNhap.setFont(new Font(btn_PhieuNhap.getFont().getName(),Font.BOLD,btn_PhieuNhap.getFont().getSize()));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/nhapkho.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_PhieuNhap.setIcon(newIcon);
        
        btn_PhieuNhap.setVerticalTextPosition(SwingConstants.CENTER);
        btn_PhieuNhap.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn_PhieuNhap.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam phieu nhap");
//                ThemNhanVien_Dialog(e);
            }
        });
        
        pane_Search.add(btn_PhieuNhap);
    }  

    public void btn_PhieuXuat(){
        btn_PhieuXuat = new ButtonGradient();
        btn_PhieuXuat.setColor1(new Color(225,244,255));
        btn_PhieuXuat.setColor2(new Color(133,210,255));
        
        btn_PhieuXuat.setText("PHIẾU XUẤT");
        btn_PhieuXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_PhieuXuat.setFont(new Font(btn_PhieuXuat.getFont().getName(),Font.BOLD,btn_PhieuXuat.getFont().getSize()));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/xuatkho.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_PhieuXuat.setIcon(newIcon);
        
        btn_PhieuXuat.setVerticalTextPosition(SwingConstants.CENTER);
        btn_PhieuXuat.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn_PhieuXuat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam phieu xuat");
//                ThemNhanVien_Dialog(e);
            }
        });
        
        pane_Search.add(btn_PhieuXuat);
    }      
    
    public void btn_ThemNL(){
        JPanel pane_themNL = new JPanel();
        pane_themNL.setPreferredSize(new Dimension(700,50));
        pane_themNL.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pane_themNL.setBackground(Color.white);

        btn_ThemNL = new ButtonGradient();
        btn_ThemNL.setText("THÊM NGUYÊN LIỆU");
        btn_ThemNL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_ThemNL.setFont(new Font(btn_ThemNL.getFont().getName(),Font.BOLD,btn_ThemNL.getFont().getSize()));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/add.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_ThemNL.setIcon(newIcon);
        
        btn_ThemNL.setVerticalTextPosition(SwingConstants.CENTER);
        btn_ThemNL.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn_ThemNL.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam them NL");
//                ThemNhanVien_Dialog(e);
            }
        });
        
        pane_themNL.add(btn_ThemNL);
        pane_QLKho.add(pane_themNL);
    }  
    
    public void table_NL(){
        Scrollpane_TableKho= new JScrollPane();

        String[] columnNames = {"Mã NL", "Tên nguyên liệu", "Đơn giá", "Đơn vị", "Tổng số lượng", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };
            
            @Override
            public boolean isCellEditable(int row, int column) {
               return canEdit [column];
            }
        };
        table_Kho = new JTable(model);
        table_Kho.setRowHeight(40);
        table_Kho.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(234,247,255) : new Color(255, 237, 243));
                c.setForeground(Color.black);
                return c;
            }
        });        
        
        table_Kho.getTableHeader().setOpaque(false);
        table_Kho.getTableHeader().setBackground(new Color(167,222,254));
        table_Kho.getTableHeader().setForeground(Color.black);
        table_Kho.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table_Kho.getTableHeader().setPreferredSize(new Dimension(table_Kho.getWidth(),40));
        table_Kho.getTableHeader().setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
                
        table_Kho.setShowHorizontalLines(false);
        table_Kho.setGridColor(Color.white);
        table_Kho.setBackground(Color.white);
        table_Kho.setBorder(new EmptyBorder(5, 5, 5,5));
        table_Kho.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        table_NV.setFont(new Font(table_NV.getFont().getName(),Font.PLAIN,14));
        
        TableColumnModel columnModel = table_Kho.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);
        try{
            String sql = "SELECT * FROM KHONGUYENLIEU ORDER BY TO_NUMBER(SUBSTR( MANL, 3 ))";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while(res.next()){
                String MANL = res.getString("MANL");
                String TENNL = res.getString("TENNL");
                String DONGIA = res.getString("DONGIA");
                String DONVI = res.getString("DONVI");
                String TONGSL = res.getString("TONGSL");

                Object tbdata[] = {MANL, TENNL, DONGIA, DONVI, TONGSL, null};
                DefaultTableModel tbmodel = (DefaultTableModel)table_Kho.getModel();
                tbmodel.addRow(tbdata);
            }
        }
        catch(SQLException | HeadlessException ex){
                    System.out.println("the error is"+ex);
        }
        
        table_Kho.setPreferredScrollableViewportSize(table_Kho.getPreferredSize());
        table_Kho.setFillsViewportHeight(true);
//        table_NV.setSelectionBackground(new Color(56, 138, 112));
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);
//                SuaNhanVien_Dialog(row);
            }

            @Override
            public void onDelete(int row) {
                if(table_Kho.isEditing()) {
                    table_Kho.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table_Kho.getModel();
                Object value_MANV = model.getValueAt(row, 0);
                System.out.println(value_MANV);
                Object value_TENTK = model.getValueAt(row, 0);
                System.out.println(value_TENTK);
                try {
                    Statement statement = connection.createStatement();
                    Delete_Confirm_jOptionPane.setVisible(true);
                    int flag_OK = Delete_Confirm_jOptionPane.showConfirmDialog(formNL_jDialog, "Bạn chắc chắn muốn xóa nguyên liệu?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(flag_OK == JOptionPane.OK_OPTION){
//                        String sql = "DELETE FROM KHONGUYENLIEU WHERE MANL = '" + value_MANV + "'";
//                        int res = statement.executeUpdate(sql);
//                        System.out.println("Delete NV thanh cong");
//                        sql = "DELETE FROM TAIKHOAN WHERE TENTK = '" + value_TENTK + "'";
//                        res = statement.executeUpdate(sql);
//                        System.out.println("Delete TK thanh cong");
                        model.removeRow(row);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        table_Kho.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table_Kho.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        
        Scrollpane_TableKho.setViewportView(table_Kho);
        Scrollpane_TableKho.setBorder(new LineBorder( Color.LIGHT_GRAY, 1, true));
        Scrollpane_TableKho.setPreferredSize(new Dimension(700, 400));
        pane_QLKho.add(Scrollpane_TableKho);
    }
    
    private void Number_keyPressed(KeyEvent e, JTextField txt, int len) {
        String PhoneNumber = txt.getText();
        int length = PhoneNumber.length();
        char c = e.getKeyChar();
        if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9'){
            if(length < len)
                txt.setEditable(true);
            else 
                txt.setEditable(false);
        }
        else {
            if(e.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode()==KeyEvent.VK_DELETE)
                txt.setEditable(true);
            else 
                txt.setEditable(false);
        }
    }
//    
//    private void Exit_Dialog_jButtonActionPerformed(ActionEvent evt) {
//        formNV_jDialog.setVisible(false);
//    }
//
//    private void ThemNhanVien_Dialog(ActionEvent evt) {
////        Dialog_form(true, -1);
//        formNV_jDialog.pack();
//        formNV_jDialog.setLocationRelativeTo(null);
//        formNV_jDialog.setVisible(true);
//    }
//    private void setText_nextMANV(){
//        String MANV;
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT TO_NUMBER(SUBSTR(MANV, 3))+1 NUM_NV FROM NHANVIEN ORDER BY TO_NUMBER(SUBSTR( MANV, 3 )) DESC";
//            ResultSet res = statement.executeQuery(sql);
//            while(res.next()){
//                MANV = "NV" + res.getInt("NUM_NV");
//                txt_IDNhanVien.setText(MANV);
//                txt_IDNhanVien.setForeground(new Color (134, 134, 134));
//                txt_IDNhanVien.setEditable(false);
//                System.out.println("set " + MANV + " thanh cong" );
//                break;
//            }
//        }
//        catch(SQLException | HeadlessException ex){
//            System.out.println("the error is "+ex);
//        }
//    }
//    private void themNV_jButtonActionPerformed(ActionEvent evt){
//        String MaNV = txt_IDNhanVien.getText();            
//        String HoTen = txt_HoTen.getText();
//        String CCCD = txt_CCCD.getText();
//        String DiaChi = txt_DiaChi.getText();
//        String SDT = txt_SDT.getText();
//        Object GioiTinh = txt_GioiTinh.getItemAt(txt_GioiTinh.getSelectedIndex());
//        String Email = txt_Email.getText();
//        String NgSinh = txt_NgaySinh.getText();
//        String ChucVu = txt_ChucVu.getText();
//        String NVL = txt_NgayVaoLam.getText();
//        Object Luong = txt_Luong.getValue();
//        
//
//                    
//        try {
//            Statement statement = connection.createStatement();
//            if (HoTen.equals("") || CCCD.equals("") || DiaChi.equals("") || SDT.equals("") || GioiTinh.equals("") || Email.equals("") || NgSinh.equals("") || ChucVu.equals("") || NVL.equals("") || Luong.equals("")) {
//                ThieuThongTin_jOptionPane.setVisible(true);
//                ThieuThongTin_jOptionPane.showMessageDialog(formNV_jDialog, "Vui lòng nhập đầy đủ thông tin!");
//                ThieuThongTin_jOptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
//            } else {
//                boolean flag_CCCDtontai = false;
//                String sql = "SELECT * FROM NHANVIEN WHERE CCCD = '" + CCCD + "'";
//                ResultSet res_select = statement.executeQuery(sql);
//                while (res_select.next()) {
//                    flag_CCCDtontai = true;
//                }
//                if (flag_CCCDtontai) {
//                    CCCDTontai_jOptionPane.setVisible(true);
//                    CCCDTontai_jOptionPane.showMessageDialog(formNV_jDialog, "CMND/CCCD đã tồn tại!");
//                    CCCDTontai_jOptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
//                } else {
//                    sql = "INSERT INTO NHANVIEN VALUES (  '" + MaNV + "' , '" + HoTen + "', '" + CCCD + "', '" + DiaChi + "', '" + SDT + "' , '" + Email + "' , '" + GioiTinh + "' , TO_DATE('" + NgSinh + "', 'DD-MM-YYYY'), TO_DATE('" + NVL + "', 'DD-MM-YYYY'), '" + ChucVu + "' , '" + Luong + "', '' )";
//                    int res = statement.executeUpdate(sql);
//                    System.out.println("Insert thanh cong");
//                    themNV_jOptionPane.setVisible(true);
//                    themNV_jOptionPane.showMessageDialog(formNV_jDialog, "Thêm thành công nhân viên!");
//                    formNV_jDialog.setVisible(false);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        try{
//            String sql= "SELECT * FROM NHANVIEN WHERE MANV = '" + MaNV +"'";
//            Statement statement = connection.createStatement();
//            ResultSet res = statement.executeQuery(sql);
//
//            while(res.next()){
//                MaNV = res.getString("MANV");
//                HoTen = res.getString("HOTEN");
//                GioiTinh = res.getString("GIOITINH");
//                NVL = res.getString("NGVL");
//                ChucVu = res.getString("CHUCVU");
//                Luong = res.getObject("LUONG");
//                String TENTK = res.getString("TENTK");
//
//                
//                Object tbdata[] = {MaNV, HoTen, GioiTinh, NVL, ChucVu, Luong, TENTK, null};
//                DefaultTableModel tbmodel = (DefaultTableModel)table_NV.getModel();
//                tbmodel.addRow(tbdata);
//                break;
//            }
//        }
//        catch(SQLException | HeadlessException ex){
//                    System.out.println("the error is "+ex);
//        }
//    }
//
//    private void SuaNhanVien_Dialog(int row) {
//        Dialog_form(false, row);
//        formNV_jDialog.pack();
//        formNV_jDialog.setLocationRelativeTo(null);
//        formNV_jDialog.setVisible(true);
//    }
//    private void setText_currMANV(int row){
//        DefaultTableModel model = (DefaultTableModel) table_NV.getModel();
//        Object value_MANV = model.getValueAt(row, 0);
//        System.out.println(value_MANV);
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT MANV, HOTEN, CCCD, DIACHI, SDT, EMAIL, GIOITINH, "
//                    + "TO_CHAR(NGSINH, 'DD-MM-YYYY') as NGSINH, "
//                    + "TO_CHAR(NGVL, 'DD-MM-YYYY') as NGVL, "
//                    + "CHUCVU, LUONG, TENTK "
//                    + "FROM NHANVIEN WHERE MANV = '" + value_MANV + "'";
//            ResultSet res = statement.executeQuery(sql);
//            System.out.println(value_MANV + " thanh cong");
//
//            while (res.next()) {
//                String MaNV = res.getString("MANV");
//                String HoTen = res.getString("HOTEN");
//                String CCCD = res.getString("CCCD");
//                String DiaChi = res.getString("DIACHI");
//                String SDT = res.getString("SDT").substring(1);
//                String Email = res.getString("EMAIL");
//                String GioiTinh = res.getString("GIOITINH");
//                String NgSinh = res.getString("NGSINH");
//                String NgVL = res.getString("NGVL");
//                String ChucVu = res.getString("CHUCVU");
//                String StrLuong = res.getString("LUONG");
//                int Luong;
//                if(StrLuong == null) 
//                    Luong = 0;
//                else 
//                    Luong = Integer.parseInt(StrLuong);
//                String TENTK = res.getString("TENTK");
//
//                txt_IDNhanVien.setText(MaNV);
//                txt_IDNhanVien.setForeground(new Color(134, 134, 134));
//                txt_IDNhanVien.setEditable(false);
//
//                txt_HoTen.setText(HoTen);
//                txt_CCCD.setText(CCCD);
//                txt_DiaChi.setText(DiaChi);
//                txt_SDT.setText(SDT);
//                txt_Email.setText(Email);
//
//                if (GioiTinh == "Nam")
//                    txt_GioiTinh.setSelectedIndex(0);
//                else
//                    txt_GioiTinh.setSelectedIndex(1);
//                txt_NgaySinh.setText(NgSinh);
//                txt_NgayVaoLam.setText(NgVL);
//                txt_ChucVu.setText(ChucVu);
//                txt_Luong.setValue(Luong);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    private void suaNV_jButtonActionPerformed(ActionEvent evt, int row){
//        DefaultTableModel model = (DefaultTableModel) table_NV.getModel();
//
//        String MaNV = txt_IDNhanVien.getText();
//        String HoTen = txt_HoTen.getText();
//        String CCCD = txt_CCCD.getText();
//        String DiaChi = txt_DiaChi.getText();
//        String SDT = txt_SDT.getText();
//        String Email = txt_Email.getText();
//        Object GioiTinh = txt_GioiTinh.getItemAt(txt_GioiTinh.getSelectedIndex());
//        String NgSinh = txt_NgaySinh.getText();
//        String NVL = txt_NgayVaoLam.getText();
//        String ChucVu = txt_ChucVu.getText();
//        Object Luong = txt_Luong.getValue();
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "UPDATE NHANVIEN SET HOTEN = '"+HoTen+"', CCCD = '" +CCCD+ "', DIACHI = '" +DiaChi+ "', SDT = '"+SDT+"', EMAIL = '"+Email+"', GIOITINH = '"+GioiTinh+"', NGSINH = TO_DATE('"+NgSinh+"', 'DD-MM-YYYY'), NGVL = TO_DATE('"+NVL+"', 'DD-MM-YYYY'), CHUCVU = '"+ChucVu+"', LUONG = "+Luong+", TENTK = '' WHERE MANV = '" + MaNV + "'";
//            int res = statement.executeUpdate(sql); 
//            suaNV_jOptionPane.setVisible(true);
//            suaNV_jOptionPane.showMessageDialog(formNV_jDialog, "Cập nhật nhân viên thành công!");
//            formNV_jDialog.setVisible(false);
//            System.out.println("Update TK thanh cong");
//            
//            model.setValueAt(HoTen, row, 1);
//            model.setValueAt(GioiTinh, row, 2);
//            model.setValueAt(NVL, row, 3);
//            model.setValueAt(ChucVu, row, 4);
//            model.setValueAt(Luong, row, 5);
//        } catch (SQLException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
