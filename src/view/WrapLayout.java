/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author My PC
 */
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class WrapLayout extends FlowLayout {

    private Dimension preferredLayoutSize;

    public WrapLayout() {
        super();
    }

    public WrapLayout(int align) {
        super(align);
    }

    public WrapLayout(int align, int hgap, int vgap) {
        super(align, hgap, vgap);
    }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        return layoutSize(target, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container target) {
        Dimension minimum = layoutSize(target, false);
        minimum.width -= (getHgap() + 1);
        return minimum;
    }

    private Dimension layoutSize(Container target, boolean preferred) {
        synchronized (target.getTreeLock()) {
            int targetWidth = target.getSize().width;

            if (targetWidth == 0) {
                targetWidth = Integer.MAX_VALUE;
            }

            int hgap = getHgap();
            int vgap = getVgap();
            Insets insets = target.getInsets();
            int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
            int maxWidth = targetWidth - horizontalInsetsAndGap;

            Dimension dim = new Dimension(0, 0);
            int rowWidth = 0;
            int rowHeight = 0;

            int nmembers = target.getComponentCount();

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);

                if (m.isVisible()) {
                    Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

                    if (rowWidth + d.width > maxWidth) {
                        addRow(dim, rowWidth, rowHeight);
                        rowWidth = 0;
                        rowHeight = 0;
                    }

                    if (rowWidth != 0) {
                        rowWidth += hgap;
                    }

                    rowWidth += d.width;
                    rowHeight = Math.max(rowHeight, d.height);
                }
            }

            addRow(dim, rowWidth, rowHeight);

            dim.width += horizontalInsetsAndGap;
            dim.height += insets.top + insets.bottom + vgap * 2;

            Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);
            if (scrollPane != null) {
                dim.width -= (hgap + 1);
            }

            return dim;
        }
    }

    private void addRow(Dimension dim, int rowWidth, int rowHeight) {
        dim.width = Math.max(dim.width, rowWidth);

        if (dim.height > 0) {
            dim.height += getVgap();
        }

        dim.height += rowHeight;
    }
    public static void main(String args[]){
        JFrame mainFrame = new JFrame();
        mainFrame.setPreferredSize(new Dimension(800, 500));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            
        JPanel containingPanel = new JPanel();
        containingPanel.setLayout(new GridBagLayout());
       containingPanel.setBackground(Color.white);

        mainFrame.add(containingPanel);

        JPanel demoLayout = new JPanel(new FlowLayout(FlowLayout.CENTER));
            demoLayout.setLayout(new WrapLayout());
            demoLayout.setSize(new Dimension(450, 1));
//        demoLayout.setMaximumSize(new Dimension(500, Short.MAX_VALUE));
        JScrollPane Scrollpane_ThucDon = new JScrollPane();
        
        for (int i = 0; i < 40; i++) {
            Font font = new Font("Arial",Font.BOLD, 20);
            JLabel testLabel = new JLabel("CAMARCHEPO");
            testLabel.setFont(font);
            demoLayout.add(testLabel);
        }

       demoLayout.revalidate();
        Scrollpane_ThucDon.setViewportView(demoLayout);
        Scrollpane_ThucDon.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scrollpane_ThucDon.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Scrollpane_ThucDon.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        Scrollpane_ThucDon.setPreferredSize(new Dimension(450, 390));
       containingPanel.add(Scrollpane_ThucDon);

       mainFrame.pack();
       mainFrame.setVisible(true);
    }
}
