package UI;

import Country.Settlement;

import javax.swing.table.AbstractTableModel;

public class StatModel extends AbstractTableModel {
    private final Settlement[] settlements;
    private final String[] titles = {"Name", "Type", "RamzorColor", "Sick Percent", "Vaccine Dose", "Dead", "Current Population"};
    public StatModel(Settlement[] settlements){
        super();
        this.settlements = settlements;
    }

    @Override
    public int getRowCount() {
        return settlements.length;
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public String getColumnName(int column) {
        return titles[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return settlements[rowIndex].getName();
            case 1:
                return settlements[rowIndex].getClass().getSimpleName();
            case 2:
                return settlements[rowIndex].getRamzorColor().toString();
            case 3:
                return String.valueOf(settlements[rowIndex].getSickPercent());
            case 4:
                return String.valueOf(settlements[rowIndex].getGivenVaccineDose());
            case 5:
                return String.valueOf(settlements[rowIndex].getDead());
            case 6:
                return String.valueOf(settlements[rowIndex].getPeople().size());
            default:
                return null;
        }
    }
}

