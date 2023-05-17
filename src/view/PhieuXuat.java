/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

/**
 *
 * @author My PC
 */
public class PhieuXuat {
    private JPanel pane_bg;
    
    private JPanel pane_QLPhieuXuat;
    private DropShadowPane pane_shadow;
    private JTable table_PhieuXuat;
    private JScrollPane Scrollpane_TablePhieuXuat;
    
    public ButtonGradient btn_PhieuNhap;
    public ButtonGradient btn_Kho;
    public ButtonGradient btn_ThemPhieuXuat;
        
    public JPanel pane_Search;
    public JButton btn_Search;
    public JComboBox boxSearch;
    public JTextField txtSearch;
    
    private JPanel pane_textPhieuXuat;    
    
    public JPanel pane_bg_ThemPhieuXuat;
    private JOptionPane Delete_Confirm_jOptionPane = new JOptionPane();
    private Connection connection;
    
    public PhieuXuat(Connection connection){        
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
        
        pane_QLPhieuXuat = new JPanel();
        pane_QLPhieuXuat.setBackground(Color.white);
        pane_QLPhieuXuat.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        pane_QLPhieuXuat.setPreferredSize(new Dimension(760, 540)); 
    }   
      
    public JPanel pane_PhieuXuat(){
        search_bar();
        pane_textPhieuXuat();
        table_NL();
        btn_ThemPhieuXuat();
        pane_shadow.add(pane_QLPhieuXuat);
        pane_bg.add(pane_shadow);
        return pane_bg;
    }
    
    
    public void search_bar(){
        pane_Search = new JPanel();
        pane_Search.setBackground(Color.white);
        pane_Search.setPreferredSize(new Dimension(800, 50)); 
        pane_Search.setBorder(new EmptyBorder(10, 0, 0, 0));
        pane_Search.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));
        
        pane_search();
        pane_ThemPhieuXuat();
        
        btn_PhieuNhap();
        btn_Kho();
        
        pane_QLPhieuXuat.add(pane_Search);
    }
    
    public void pane_search(){
        JPanel pane_search_bar = new JPanel();
        pane_search_bar.setBackground(Color.white );
        pane_search_bar.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        txtSearch = new JTextField(" Search");
        txtSearch.setPreferredSize(new Dimension(50, 31)); 
        txtSearch.setColumns(25);     
        txtSearch.setForeground(Color.GRAY);
        txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSearch.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                txtSearch.setText("");
                txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
                txtSearch.setForeground(Color.BLACK);
            }
            public void focusLost(FocusEvent e) {
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
        
        String colname_NL[] = {"MAPX", "MANL", "SL", "NGAYXUAT"};
        boxSearch = new JComboBox(colname_NL);
        boxSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boxSearch.setSelectedItem("MAPX");
        boxSearch.setFont(new Font("SansSerif", Font.BOLD, 14));
        boxSearch.setBackground(Color.white);
        boxSearch.setPreferredSize(new Dimension(135, 35)); 

        Search();
        
        pane_search_bar.setBorder(txtSearch.getBorder());
        txtSearch.setBorder(null);
        btn_Search.setBorder(null);
        
        pane_Search.add(pane_search_bar);
        pane_Search.add(boxSearch);
    }
    
    public void pane_ThemPhieuXuat(){
        pane_bg_ThemPhieuXuat = new JPanel();
        pane_bg_ThemPhieuXuat.setBackground(Color.white);
        pane_bg_ThemPhieuXuat.setPreferredSize(new Dimension(600, 500));
        pane_bg_ThemPhieuXuat.setBounds(70, 30, 600, 500);
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
                System.out.println("Da bam tim kiem phieu xuat");
                try{
                    String sql = "SELECT * FROM KHONGUYENLIEU WHERE "+ boxSearch.getSelectedItem().toString() + " LIKE '%" + txtSearch.getText() + "%'";
                    Statement statement = connection.createStatement();
                    ResultSet res = statement.executeQuery(sql);
                    
                    DefaultTableModel tbmodel = (DefaultTableModel)table_PhieuXuat.getModel();
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

//    public void init_Dialog(){
//        formNL_jDialog = new JDialog();
//        formNL_jDialog.getContentPane().setBackground(new Color(255, 255, 255));
//        formNL_jDialog.setMinimumSize(new Dimension(700, 500));
//        formNL_jDialog.setModal(true);
//        formNL_jDialog.setResizable(false);
//        GridBagLayout jDialogLayout = new GridBagLayout();
//        jDialogLayout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
//        jDialogLayout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
//        formNL_jDialog.getContentPane().setLayout(jDialogLayout);
//    }
     
    public void btn_Kho(){
        btn_Kho = new ButtonGradient();
        
        btn_Kho.setText("KHO");
        btn_Kho.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_Kho.setFont(new Font(btn_Kho.getFont().getName(),Font.BOLD,btn_Kho.getFont().getSize()));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/cardboard.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_Kho.setIcon(newIcon);
        
        btn_Kho.setVerticalTextPosition(SwingConstants.CENTER);
        btn_Kho.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn_Kho.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Da bam kho");
//                PhieuNhap_Dialog(e);
            }
        });
        
        pane_Search.add(btn_Kho);
    }  

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
                System.out.println("Da bam phieu xuat");
//                PhieuXuat_Dialog(e);
            }
        });
        
        pane_Search.add(btn_PhieuNhap);
    }      
    
    public void btn_ThemPhieuXuat(){
        JPanel pane_ThemPhieuXuat = new JPanel();
        pane_ThemPhieuXuat.setPreferredSize(new Dimension(700,50));
        pane_ThemPhieuXuat.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pane_ThemPhieuXuat.setBackground(Color.white);

        btn_ThemPhieuXuat = new ButtonGradient();
        btn_ThemPhieuXuat.setColor1(new Color(225,244,255));
        btn_ThemPhieuXuat.setColor2(new Color(133,210,255));
        btn_ThemPhieuXuat.setText("THÊM PHIẾU XUẤT");
        btn_ThemPhieuXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_ThemPhieuXuat.setFont(new Font(btn_ThemPhieuXuat.getFont().getName(),Font.BOLD,btn_ThemPhieuXuat.getFont().getSize()));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/add.png"));
        Image image = icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image); 
        btn_ThemPhieuXuat.setIcon(newIcon);
        
        btn_ThemPhieuXuat.setVerticalTextPosition(SwingConstants.CENTER);
        btn_ThemPhieuXuat.setHorizontalTextPosition(SwingConstants.RIGHT);
//        btn_ThemPhieuXuat.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                System.out.println("Da bam them phieu xuat");
////                ThemNguyenLieu_Dialog(e);
//            }
//        });
        
        pane_ThemPhieuXuat.add(btn_ThemPhieuXuat);
        pane_QLPhieuXuat.add(pane_ThemPhieuXuat);
    }  
    
    private void pane_textPhieuXuat(){
        JLabel textPhieuXuat = new JLabel("PHIẾU XUẤT");
        textPhieuXuat.setFont(new Font(textPhieuXuat.getFont().getName(), Font.BOLD, 20));
        
        pane_textPhieuXuat = new JPanel();
        pane_textPhieuXuat.setBackground(new Color(167,222,254));
        pane_textPhieuXuat.setPreferredSize(new Dimension(760, 50));
        pane_textPhieuXuat.setBorder(new EmptyBorder(7, 0, 0,0));
        
        pane_textPhieuXuat.add(textPhieuXuat);
        pane_QLPhieuXuat.add(pane_textPhieuXuat);
    }    
    
    public void table_NL(){
        Scrollpane_TablePhieuXuat= new JScrollPane();

        String[] columnNames = {"Mã phiếu xuất", "Mã nguyên liệu", "Số lượng", "Ngày xuất", ""};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };
            
            @Override
            public boolean isCellEditable(int row, int column) {
               return canEdit [column];
            }
        };
        table_PhieuXuat = new JTable(model);
        table_PhieuXuat.setRowHeight(40);
        table_PhieuXuat.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(246,253,255): new Color(229,246,255));
                c.setForeground(Color.black);
                return c;
            }
        });        
        
        table_PhieuXuat.getTableHeader().setOpaque(false);
        table_PhieuXuat.getTableHeader().setBackground(new Color(167,222,254));
        table_PhieuXuat.getTableHeader().setForeground(Color.black);
        table_PhieuXuat.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table_PhieuXuat.getTableHeader().setPreferredSize(new Dimension(table_PhieuXuat.getWidth(),40));
        table_PhieuXuat.getTableHeader().setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
                
        table_PhieuXuat.setShowHorizontalLines(false);
        table_PhieuXuat.setGridColor(Color.white);
        table_PhieuXuat.setBackground(Color.white);
        table_PhieuXuat.setBorder(new EmptyBorder(5, 5, 5,5));
        table_PhieuXuat.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        table_NV.setFont(new Font(table_NV.getFont().getName(),Font.PLAIN,14));
        
        TableColumnModel columnModel = table_PhieuXuat.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(100);
        
        add_data_table();
        
        table_PhieuXuat.setPreferredScrollableViewportSize(table_PhieuXuat.getPreferredSize());
        table_PhieuXuat.setFillsViewportHeight(true);
//        table_NV.setSelectionBackground(new Color(56, 138, 112));
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);
//                SuaNhanVien_Dialog(row);
            }

            @Override
            public void onDelete(int row) {
                if(table_PhieuXuat.isEditing()) {
                    table_PhieuXuat.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table_PhieuXuat.getModel();
                Object value_MANV = model.getValueAt(row, 0);
                System.out.println(value_MANV);
                Object value_TENTK = model.getValueAt(row, 0);
                System.out.println(value_TENTK);
                try {
                    Statement statement = connection.createStatement();
                    Delete_Confirm_jOptionPane.setVisible(true);
//                    int flag_OK = Delete_Confirm_jOptionPane.showConfirmDialog(formNL_jDialog, "Bạn chắc chắn muốn xóa nguyên liệu?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                    if(flag_OK == JOptionPane.OK_OPTION){
////                        String sql = "DELETE FROM KHONGUYENLIEU WHERE MANL = '" + value_MANV + "'";
////                        int res = statement.executeUpdate(sql);
////                        System.out.println("Delete NV thanh cong");
////                        sql = "DELETE FROM TAIKHOAN WHERE TENTK = '" + value_TENTK + "'";
////                        res = statement.executeUpdate(sql);
////                        System.out.println("Delete TK thanh cong");
//                        model.removeRow(row);
//                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        table_PhieuXuat.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender(new Color(246,253,255), new Color(229,246,255)));
        table_PhieuXuat.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event, new Color(246,253,255), new Color(229,246,255)));
        
        Scrollpane_TablePhieuXuat.setViewportView(table_PhieuXuat);
        Scrollpane_TablePhieuXuat.setBorder(new LineBorder( Color.LIGHT_GRAY, 1, true));
        Scrollpane_TablePhieuXuat.setPreferredSize(new Dimension(700, 330));
        pane_QLPhieuXuat.add(Scrollpane_TablePhieuXuat);
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
    
    public void add_data_table(){
        try{
            String sql = "SELECT MAPX, MANL, SL, "
                    + "TO_CHAR(NGAYXUAT, 'DD-MM-YYYY') as NGAYXUAT "
                    + "FROM PHIEUXUAT ORDER BY TO_NUMBER(SUBSTR( MAPX, 3 ))";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while(res.next()){
                String MAPX = res.getString("MAPX");
                String MANL = res.getString("MANL");
                String SL = res.getString("SL");
                String NGAYXUAT = res.getString("NGAYXUAT");

                Object tbdata[] = {MAPX, MANL, SL, NGAYXUAT};
                DefaultTableModel tbmodel = (DefaultTableModel)table_PhieuXuat.getModel();
                tbmodel.addRow(tbdata);
            }
        }
        catch(SQLException | HeadlessException ex){
                    System.out.println("the error is"+ex);
        }
    }
    
}
