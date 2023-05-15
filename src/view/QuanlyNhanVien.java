/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.raven.datechooser.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import test.DropShadowJPanel;

public class QuanlyNhanVien {
    private JPanel pane_bg;
    
    private JPanel pane_QLNV;
    private DropShadowPane pane_shadow;
    private JTable table_NV;
    private JScrollPane Scrollpane_TableNV;
    public ButtonGradient btn_ThemNV;
    public JPanel pane_Search;
    public JButton btn_Search;
    public JComboBox boxSearch;
    public JTextField txtSearch;
    
    public JDialog formNV_jDialog;
    public JPanel pane_bg_ThemNV;
    public JPanel pane_ThemNV;
    public JOptionPane themNV_jOptionPane = new JOptionPane();
    public JOptionPane suaNV_jOptionPane = new JOptionPane();
    public JOptionPane confirm_xoaNV_jOptionPane = new JOptionPane();
    
    private JTextField txt_IDNhanVien;
    private JTextField txt_HoTen;
    private JTextField txt_CCCD;
    private PrefixTextField txt_SDT;
    private JComboBox txt_GioiTinh;
    private JTextField txt_Email;
    private JTextField txt_NgaySinh;
    private JTextField txt_ChucVu;
    private JTextField txt_NgayVaoLam;
    private JFormattedTextField txt_Luong;
    private JTextField txt_DiaChi;

    private JOptionPane ThieuThongTin_jOptionPane = new JOptionPane();
    private JOptionPane CCCDTontai_jOptionPane = new JOptionPane();
    private JOptionPane Delete_Confirm_jOptionPane = new JOptionPane();
    
    private Connection connection;
    
    public QuanlyNhanVien(Connection connection){
        this.connection = connection;
        init_pane();
    }
    
    public void init_pane(){
        pane_bg = new JPanel();
        pane_bg.setOpaque(true);
        pane_bg.setBackground(new Color(230, 235, 240));
//        pane_bg.setBackground(new Color(215, 220, 230));

        pane_bg.setPreferredSize(new Dimension(800, 600)); 
        
        pane_shadow = new DropShadowPane(4, 0, 0, 0, Color.white, 50);
        pane_shadow.setPreferredSize(new Dimension(770, 550)); 
        
        pane_QLNV = new JPanel();
        pane_QLNV.setBackground(Color.white);
        pane_QLNV.setPreferredSize(new Dimension(760, 540)); 
    }    
    public JPanel pane_QLNV(){
        search_bar();
        table_NV();
        pane_shadow.add(pane_QLNV);
        pane_bg.add(pane_shadow);
//        pane_bg.add(pane_QLNV);
        return pane_bg;
    }
    
    public void search_bar(){
        pane_Search = new JPanel();
        pane_Search.setBackground(Color.white);
        pane_Search.setPreferredSize(new Dimension(800, 70)); 
        pane_search();
        pane_ThemNV();
        btn_ThemNV();
        pane_QLNV.add(pane_Search);
    }
    
    public void pane_search(){
        JPanel pane_search_bar = new JPanel();
        pane_search_bar.setBackground(Color.white );
        pane_search_bar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        txtSearch = new JTextField(" Search");
        txtSearch.setPreferredSize(new Dimension(50, 31)); 
        txtSearch.setColumns(35);     
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
        
        String colname_NV[] = { "MANV", "HOTEN", "CCCD", "DIACHI", "SDT", "EMAIL", "GIOITINH", "NGSINH", "NGVL", "CHUCVU", "LUONG", "TENTK" };
        boxSearch = new JComboBox(colname_NV);
        boxSearch.setSelectedItem("HOTEN");
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
    
    public void pane_ThemNV(){
        pane_bg_ThemNV = new JPanel();
        pane_bg_ThemNV.setBackground(new Color(0,0,0,50));
        pane_bg_ThemNV.setPreferredSize(new Dimension(769, 550));
        pane_bg_ThemNV.setBounds(8, 7, 769, 550);
        
        pane_ThemNV = new JPanel();
        pane_ThemNV.setBackground(Color.white);
        pane_ThemNV.setPreferredSize(new Dimension(600, 500));
        pane_ThemNV.setBounds(70, 30, 600, 500);
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
                System.out.println("Da bam tim kiem NV");
                try{
                    String sql = "SELECT MANV, HOTEN, CCCD, DIACHI, SDT, EMAIL, GIOITINH, "
                    + "TO_CHAR(NGSINH, 'DD-MM-YYYY') as NGSINH, "
                    + "TO_CHAR(NGVL, 'DD-MM-YYYY') as NGVL, "
                    + "CHUCVU, LUONG, TENTK "
                    + "FROM NHANVIEN WHERE "
                    + boxSearch.getSelectedItem().toString() + " LIKE '%" + txtSearch.getText() + "%'";
                    Statement statement = connection.createStatement();
                    ResultSet res = statement.executeQuery(sql);
                    
                    DefaultTableModel tbmodel = (DefaultTableModel)table_NV.getModel();
                    tbmodel.setRowCount(0);

                    while(res.next()){
                        String MANV = res.getString("MANV");
                        String HOTEN = res.getString("HOTEN");
                        String GIOITINH = res.getString("GIOITINH");
                        String NGVL = res.getString("NGVL");
                        String CHUCVU = res.getString("CHUCVU");
                        Object LUONG = res.getObject("LUONG");
                        String TENTK = res.getString("TENTK");

                        Object tbdata[] = {MANV, HOTEN, GIOITINH, NGVL, CHUCVU, LUONG, TENTK, null};
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
        formNV_jDialog = new JDialog();
        formNV_jDialog.getContentPane().setBackground(new Color(255, 255, 255));
        formNV_jDialog.setMinimumSize(new Dimension(700, 500));
        formNV_jDialog.setModal(true);
        formNV_jDialog.setResizable(false);
        GridBagLayout jDialogLayout = new GridBagLayout();
        jDialogLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jDialogLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        formNV_jDialog.getContentPane().setLayout(jDialogLayout);
    }
    
    public void Dialog_form( boolean loaiDialog, int row){
        boolean loai = loaiDialog;
        init_Dialog();
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel IDNhanVien = new JLabel("ID Nhân viên:");
        JLabel HoTen = new JLabel("Họ tên:");
        JLabel CCCD = new JLabel("CMND/CCCD:");
        JLabel SDT = new JLabel("Số điện thoại:");
        JLabel GioiTinh = new JLabel("Giới tính");
        JLabel Email = new JLabel("E-mail:");
        JLabel NgaySinh = new JLabel("Ngày sinh:");
        JLabel ChucVu = new JLabel("Chức vụ:");
        JLabel NgayVaoLam = new JLabel("Ngày vào làm:");
        JLabel Luong = new JLabel("Lương:");
        JLabel DiaChi = new JLabel("Địa chỉ:");
        
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        
        txt_IDNhanVien = new JTextField();
        txt_HoTen = new JTextField();
        
        txt_CCCD = new JTextField();
        txt_CCCD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Number_keyPressed(e, txt_CCCD, 12);
            };
        });
        
        txt_SDT = new PrefixTextField("0");
        txt_SDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Number_keyPressed(e, txt_SDT, 10);
            };
        });

        String StrGioiTinh[] = {"Nam", "Nữ"};
        txt_GioiTinh = new JComboBox(StrGioiTinh);
        txt_Email = new JTextField();
        txt_NgaySinh = new JTextField();
        DateChooser ngaySinh_dateChooser = new DateChooser();
        ngaySinh_dateChooser.setForeground(new java.awt.Color(167, 223, 255));
        ngaySinh_dateChooser.setTextRefernce(txt_NgaySinh);
        txt_ChucVu = new JTextField();
        txt_NgayVaoLam = new JTextField();
        DateChooser ngayVaoLam_dateChooser = new DateChooser();
        ngayVaoLam_dateChooser.setForeground(new java.awt.Color(255, 184, 183));
        ngayVaoLam_dateChooser.setTextRefernce(txt_NgayVaoLam);
        txt_Luong = new JFormattedTextField(formatter);
        txt_DiaChi = new JTextField();
        
        JButton btn_cancel_themNV = new JButton("HỦY");
        btn_cancel_themNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_Dialog_jButtonActionPerformed(evt);
            }
        });

        gbc = new GridBagConstraints();
        gbc.gridx = 68;
        gbc.gridy = 18;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(btn_cancel_themNV, gbc);
        
        JButton btn_XacNhan = new JButton("XÁC NHẬN");
        btn_XacNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(loai)
                    themNV_jButtonActionPerformed(evt);
                else 
                    suaNV_jButtonActionPerformed(evt, row);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 62;
        gbc.gridy = 18;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(btn_XacNhan, gbc);
        
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(IDNhanVien, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 56;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(HoTen, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(CCCD, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(SDT, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(GioiTinh, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 56;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(Email, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(NgaySinh, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 56;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(ChucVu, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(NgayVaoLam, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(Luong, gbc);

        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 12;
        gbc.gridwidth = 5;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        formNV_jDialog.getContentPane().add(DiaChi, gbc);
        
        if(loai)
            setText_nextMANV();
        else
            setText_currMANV(row);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 23;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_IDNhanVien, gbc);

        txt_HoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                hoTen_jTextFieldActionPerformed(evt);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 56;
        gbc.gridy = 2;
        gbc.gridwidth = 19;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_HoTen, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 2;
        gbc.gridwidth = 25;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_CCCD, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 23;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_SDT, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 6;
        gbc.gridwidth = 25;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_GioiTinh, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 56;
        gbc.gridy = 6;
        gbc.gridwidth = 19;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_Email, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 23;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_NgaySinh, gbc);

        txt_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                chucVu_jTextFieldActionPerformed(evt);
            }
        });
        
        gbc = new GridBagConstraints();
        gbc.gridx = 56;
        gbc.gridy = 10;
        gbc.gridwidth = 19;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_ChucVu, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 10;
        gbc.gridwidth = 25;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_NgayVaoLam, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 23;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_Luong, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 26;
        gbc.gridy = 14;
        gbc.gridwidth = 49;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0);
        formNV_jDialog.getContentPane().add(txt_DiaChi, gbc);    
    }

    public void btn_ThemNV(){
        btn_ThemNV = new ButtonGradient();
        btn_ThemNV.setText("THÊM MỚI");
        btn_ThemNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_ThemNV.setFont(new Font(btn_ThemNV.getFont().getName(),Font.BOLD,btn_ThemNV.getFont().getSize()));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/add-employee-icon.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_ThemNV.setIcon(newIcon);
        
        btn_ThemNV.setVerticalTextPosition(SwingConstants.CENTER);
        btn_ThemNV.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn_ThemNV.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam them NV");
                ThemNhanVien_Dialog(e);
            }
        });
        
        pane_Search.add(btn_ThemNV);
    }  
    
    public void table_NV(){
        Scrollpane_TableNV= new JScrollPane();

        String[] columnNames = {"Mã NV", "Họ tên", "Giới tính", "Ngày vào làm", "Chức vụ", "Lương", "Tên TK", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };
            
            @Override
            public boolean isCellEditable(int row, int column) {
               return canEdit [column];
            }
        };
        table_NV = new JTable(model);
        table_NV.setRowHeight(40);
        table_NV.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(234,247,255) : new Color(255, 237, 243));
                c.setForeground(Color.black);
                return c;
            }
        });        
        
        table_NV.getTableHeader().setOpaque(false);
        table_NV.getTableHeader().setBackground(new Color(167,222,254));
        table_NV.getTableHeader().setForeground(Color.black);
        table_NV.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
//        table_NV.getTableHeader().setFont(new Font(table_NV.getFont().getName(),Font.BOLD,14));
        table_NV.getTableHeader().setPreferredSize(new Dimension(table_NV.getWidth(),40));
        table_NV.getTableHeader().setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
                
        table_NV.setShowHorizontalLines(false);
        table_NV.setGridColor(Color.white);
        table_NV.setBackground(Color.white);
        table_NV.setBorder(new EmptyBorder(5, 5, 5,5));
        table_NV.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        table_NV.setFont(new Font(table_NV.getFont().getName(),Font.PLAIN,14));
        
        TableColumnModel columnModel = table_NV.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
//        columnModel.getColumn(7).setPreferredWidth(100);
        try{
            String sql = "SELECT MANV, HOTEN, CCCD, DIACHI, SDT, EMAIL, GIOITINH, "
                    + "TO_CHAR(NGSINH, 'DD-MM-YYYY') as NGSINH, "
                    + "TO_CHAR(NGVL, 'DD-MM-YYYY') as NGVL, "
                    + "CHUCVU, LUONG, TENTK "
                    + "FROM NHANVIEN ORDER BY TO_NUMBER(SUBSTR( MANV, 3 ))";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while(res.next()){
                String MANV = res.getString("MANV");
                String HOTEN = res.getString("HOTEN");
                String GIOITINH = res.getString("GIOITINH");
                String NGVL = res.getString("NGVL");
                String CHUCVU = res.getString("CHUCVU");
                Object LUONG = res.getObject("LUONG");
                String TENTK = res.getString("TENTK");

                Object tbdata[] = {MANV, HOTEN, GIOITINH, NGVL, CHUCVU, LUONG, TENTK, null};
                DefaultTableModel tbmodel = (DefaultTableModel)table_NV.getModel();
                tbmodel.addRow(tbdata);
            }
        }
        catch(SQLException | HeadlessException ex){
                    System.out.println("the error is"+ex);
        }
        
        table_NV.setPreferredScrollableViewportSize(table_NV.getPreferredSize());
        table_NV.setFillsViewportHeight(true);
//        table_NV.setSelectionBackground(new Color(56, 138, 112));
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);
                SuaNhanVien_Dialog(row);
            }

            @Override
            public void onDelete(int row) {
                if(table_NV.isEditing()) {
                    table_NV.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table_NV.getModel();
                Object value_MANV = model.getValueAt(row, 0);
                System.out.println(value_MANV);
                Object value_TENTK = model.getValueAt(row, 0);
                System.out.println(value_TENTK);
                try {
                    Statement statement = connection.createStatement();
                    Delete_Confirm_jOptionPane.setVisible(true);
                    int flag_OK = Delete_Confirm_jOptionPane.showConfirmDialog(formNV_jDialog, "Bạn chắc chắn muốn xóa nhân viên?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(flag_OK == JOptionPane.OK_OPTION){
                        String sql = "DELETE FROM NHANVIEN WHERE MANV = '" + value_MANV + "'";
                        int res = statement.executeUpdate(sql);
                        System.out.println("Delete NV thanh cong");
                        sql = "DELETE FROM TAIKHOAN WHERE TENTK = '" + value_TENTK + "'";
                        res = statement.executeUpdate(sql);
                        System.out.println("Delete TK thanh cong");
                        model.removeRow(row);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            @Override
//            public void onCreateAccount(int row) {}
        };
        table_NV.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        table_NV.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
        
        
        Scrollpane_TableNV.setViewportView(table_NV);
        Scrollpane_TableNV.setBorder(new LineBorder( Color.LIGHT_GRAY, 1, true));
        Scrollpane_TableNV.setPreferredSize(new Dimension(700, 450));
        pane_QLNV.add(Scrollpane_TableNV);
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
    
    private void Exit_Dialog_jButtonActionPerformed(ActionEvent evt) {
        formNV_jDialog.setVisible(false);
    }

    private void ThemNhanVien_Dialog(ActionEvent evt) {
        Dialog_form(true, -1);
        formNV_jDialog.pack();
        formNV_jDialog.setLocationRelativeTo(null);
        formNV_jDialog.setVisible(true);
    }
    private void setText_nextMANV(){
        String MANV;
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT TO_NUMBER(SUBSTR(MANV, 3))+1 NUM_NV FROM NHANVIEN ORDER BY TO_NUMBER(SUBSTR( MANV, 3 )) DESC";
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                MANV = "NV" + res.getInt("NUM_NV");
                txt_IDNhanVien.setText(MANV);
                txt_IDNhanVien.setForeground(new Color (134, 134, 134));
                txt_IDNhanVien.setEditable(false);
                System.out.println("set " + MANV + " thanh cong" );
                break;
            }
        }
        catch(SQLException | HeadlessException ex){
            System.out.println("the error is "+ex);
        }
    }
    private void themNV_jButtonActionPerformed(ActionEvent evt){
        String MaNV = txt_IDNhanVien.getText();            
        String HoTen = txt_HoTen.getText();
        String CCCD = txt_CCCD.getText();
        String DiaChi = txt_DiaChi.getText();
        String SDT = txt_SDT.getText();
        Object GioiTinh = txt_GioiTinh.getItemAt(txt_GioiTinh.getSelectedIndex());
        String Email = txt_Email.getText();
        String NgSinh = txt_NgaySinh.getText();
        String ChucVu = txt_ChucVu.getText();
        String NVL = txt_NgayVaoLam.getText();
        Object Luong = txt_Luong.getValue();
                    
        try {
            Statement statement = connection.createStatement();
            if (HoTen.equals("") || CCCD.equals("") || DiaChi.equals("") || SDT.equals("") || GioiTinh.equals("") || Email.equals("") || NgSinh.equals("") || ChucVu.equals("") || NVL.equals("") || Luong.equals("")) {
                ThieuThongTin_jOptionPane.setVisible(true);
                ThieuThongTin_jOptionPane.showMessageDialog(formNV_jDialog, "Vui lòng nhập đầy đủ thông tin!");
                ThieuThongTin_jOptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
            } else {
                boolean flag_CCCDtontai = false;
                String sql = "SELECT * FROM NHANVIEN WHERE CCCD = '" + CCCD + "'";
                ResultSet res_select = statement.executeQuery(sql);
                while (res_select.next()) {
                    flag_CCCDtontai = true;
                }
                if (flag_CCCDtontai) {
                    CCCDTontai_jOptionPane.setVisible(true);
                    CCCDTontai_jOptionPane.showMessageDialog(formNV_jDialog, "CMND/CCCD đã tồn tại!");
                    CCCDTontai_jOptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                } else {
                    sql = "INSERT INTO NHANVIEN VALUES (  '" + MaNV + "' , '" + HoTen + "', '" + CCCD + "', '" + DiaChi + "', '" + SDT + "' , '" + Email + "' , '" + GioiTinh + "' , TO_DATE('" + NgSinh + "', 'DD-MM-YYYY'), TO_DATE('" + NVL + "', 'DD-MM-YYYY'), '" + ChucVu + "' , '" + Luong + "', '' )";
                    int res = statement.executeUpdate(sql);
                    System.out.println("Insert thanh cong");
                    themNV_jOptionPane.setVisible(true);
                    themNV_jOptionPane.showMessageDialog(formNV_jDialog, "Thêm thành công nhân viên!");
                    formNV_jDialog.setVisible(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            String sql= "SELECT * FROM NHANVIEN WHERE MANV = '" + MaNV +"'";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while(res.next()){
                MaNV = res.getString("MANV");
                HoTen = res.getString("HOTEN");
                GioiTinh = res.getString("GIOITINH");
                NVL = res.getString("NGVL");
                ChucVu = res.getString("CHUCVU");
                Luong = res.getObject("LUONG");
                String TENTK = res.getString("TENTK");

                
                Object tbdata[] = {MaNV, HoTen, GioiTinh, NVL, ChucVu, Luong, TENTK, null};
                DefaultTableModel tbmodel = (DefaultTableModel)table_NV.getModel();
                tbmodel.addRow(tbdata);
                break;
            }
        }
        catch(SQLException | HeadlessException ex){
                    System.out.println("the error is "+ex);
        }
    }

    private void SuaNhanVien_Dialog(int row) {
        Dialog_form(false, row);
        formNV_jDialog.pack();
        formNV_jDialog.setLocationRelativeTo(null);
        formNV_jDialog.setVisible(true);
    }
    private void setText_currMANV(int row){
        DefaultTableModel model = (DefaultTableModel) table_NV.getModel();
        Object value_MANV = model.getValueAt(row, 0);
        System.out.println(value_MANV);
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT MANV, HOTEN, CCCD, DIACHI, SDT, EMAIL, GIOITINH, "
                    + "TO_CHAR(NGSINH, 'DD-MM-YYYY') as NGSINH, "
                    + "TO_CHAR(NGVL, 'DD-MM-YYYY') as NGVL, "
                    + "CHUCVU, LUONG, TENTK "
                    + "FROM NHANVIEN WHERE MANV = '" + value_MANV + "'";
            ResultSet res = statement.executeQuery(sql);
            System.out.println(value_MANV + " thanh cong");

            while (res.next()) {
                String MaNV = res.getString("MANV");
                String HoTen = res.getString("HOTEN");
                String CCCD = res.getString("CCCD");
                String DiaChi = res.getString("DIACHI");
                String SDT = res.getString("SDT").substring(1);
                String Email = res.getString("EMAIL");
                String GioiTinh = res.getString("GIOITINH");
                String NgSinh = res.getString("NGSINH");
                String NgVL = res.getString("NGVL");
                String ChucVu = res.getString("CHUCVU");
                String StrLuong = res.getString("LUONG");
                int Luong;
                if(StrLuong == null) 
                    Luong = 0;
                else 
                    Luong = Integer.parseInt(StrLuong);
                String TENTK = res.getString("TENTK");

                txt_IDNhanVien.setText(MaNV);
                txt_IDNhanVien.setForeground(new Color(134, 134, 134));
                txt_IDNhanVien.setEditable(false);

                txt_HoTen.setText(HoTen);
                txt_CCCD.setText(CCCD);
                txt_DiaChi.setText(DiaChi);
                txt_SDT.setText(SDT);
                txt_Email.setText(Email);

                if (GioiTinh == "Nam")
                    txt_GioiTinh.setSelectedIndex(0);
                else
                    txt_GioiTinh.setSelectedIndex(1);
                txt_NgaySinh.setText(NgSinh);
                txt_NgayVaoLam.setText(NgVL);
                txt_ChucVu.setText(ChucVu);
                txt_Luong.setValue(Luong);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void suaNV_jButtonActionPerformed(ActionEvent evt, int row){
        DefaultTableModel model = (DefaultTableModel) table_NV.getModel();

        String MaNV = txt_IDNhanVien.getText();
        String HoTen = txt_HoTen.getText();
        String CCCD = txt_CCCD.getText();
        String DiaChi = txt_DiaChi.getText();
        String SDT = txt_SDT.getText();
        String Email = txt_Email.getText();
        Object GioiTinh = txt_GioiTinh.getItemAt(txt_GioiTinh.getSelectedIndex());
        String NgSinh = txt_NgaySinh.getText();
        String NVL = txt_NgayVaoLam.getText();
        String ChucVu = txt_ChucVu.getText();
        Object Luong = txt_Luong.getValue();
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE NHANVIEN SET HOTEN = '"+HoTen+"', CCCD = '" +CCCD+ "', DIACHI = '" +DiaChi+ "', SDT = '"+SDT+"', EMAIL = '"+Email+"', GIOITINH = '"+GioiTinh+"', NGSINH = TO_DATE('"+NgSinh+"', 'DD-MM-YYYY'), NGVL = TO_DATE('"+NVL+"', 'DD-MM-YYYY'), CHUCVU = '"+ChucVu+"', LUONG = "+Luong+", TENTK = '' WHERE MANV = '" + MaNV + "'";
            int res = statement.executeUpdate(sql); 
            suaNV_jOptionPane.setVisible(true);
            suaNV_jOptionPane.showMessageDialog(formNV_jDialog, "Cập nhật nhân viên thành công!");
            formNV_jDialog.setVisible(false);
            System.out.println("Update TK thanh cong");
            
            model.setValueAt(HoTen, row, 1);
            model.setValueAt(GioiTinh, row, 2);
            model.setValueAt(NVL, row, 3);
            model.setValueAt(ChucVu, row, 4);
            model.setValueAt(Luong, row, 5);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    
//    public void uploadimg(ActionEvent e) {
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        String filename = f.getAbsolutePath();
//        txt_HoTen.setText(filename);
//        try {
//            ImageIcon ii = new ImageIcon(ImageIO.read(new File(f.getAbsolutePath())));//get the image from file chooser and scale it to match JLabel size
////        jLabel1.setIcon(ii);
//            Image image = ii.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//            ImageIcon newIcon = new ImageIcon(image);
//            JLabel j = new JLabel();
//            j.setIcon(newIcon);
////        IDNhanVien.setIcon(newIcon);
//            formNV_jDialog.add(j);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
