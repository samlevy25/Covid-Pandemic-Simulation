package UI;

import Virus.IVirus;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.Objects;

import static Virus.VirusStrategy.variantsTab;

public class EditMutationWindow extends JDialog {

    private static class VariantModel extends AbstractTableModel
    {
        private final IVirus[] virus;
        private final String[] columnNames = {"British Variant", "Chinese Variant", "South Africa Variant"};
        public VariantModel(IVirus[] virusArray) {
            this.virus = virusArray;
        } // Ctor


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
            return variantsTab[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Objects.requireNonNull(getValueAt(0, columnIndex)).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            variantsTab[rowIndex][columnIndex] = (boolean) aValue;
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
        String[] elements = {"British Variant", "Chinese Variant", "South Africa Variant"};
        this.add(new RowedTableScroll(table, elements));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        }
}

