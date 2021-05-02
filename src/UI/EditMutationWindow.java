package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditMutationWindow extends JFrame {
    private JTable editTab;

    public EditMutationWindow(){
        super("Edit Mutation");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] elements = {"British Variant", "Chinese Variant", "South Africa Variant"};
        Object[][] data = {{true, true, true}, {true, true, true},{true, true, true}};
        DefaultTableModel model = new DefaultTableModel(data, elements);
        JTable table = new JTable(model) {
            public Class getColumnClass(int column){
                return getValueAt(0, column).getClass();
            }
        };
        this.add(new RowedTableScroll(table, elements));
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
