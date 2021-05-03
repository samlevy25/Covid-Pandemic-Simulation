package UI;

import Virus.IVirus;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditMutationWindow extends JDialog {
    private String[] elements = {"British Variant", "Chinese Variant", "South Africa Variant"};
    private JTable editTab;
    private class VariantModel extends AbstractTableModel {
        private IVirus[] virus;
        private final String[] columnNames = {"British Variant", "Chinese Variant", "South Africa Variant"};
        public VariantModel(IVirus[] data) {
            this.virus = data;
        }

        @Override
        public int getRowCount() {
            return virus.length;
        }

        @Override
        public int getColumnCount() {
            return virus.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            IVirus ivirus = virus[rowIndex];
            switch(columnIndex){
                case 0: return ivirus.getMutations()[0];
                case 1: return ivirus.getMutations()[1];
                case 2: return ivirus.getMutations()[2];
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            IVirus ivirus = virus[rowIndex];
            switch(columnIndex){
                case 0: ivirus.setMutations(0, (Boolean) aValue);
                break;
                case 1: ivirus.setMutations(1, (Boolean) aValue);
                break;
                case 2: ivirus.setMutations(2, (Boolean) aValue);
                break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    public EditMutationWindow(IVirus[] virus){
        this.setModal(true);
        this.setTitle("Edit Mutation");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        VariantModel model = new VariantModel(virus);
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        this.add(new RowedTableScroll(table, elements));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        };
    }

