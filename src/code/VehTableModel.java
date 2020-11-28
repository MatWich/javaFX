package code;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VehTableModel extends AbstractTableModel {
    private List<Vehicle> data = new ArrayList<>();
    private String[] columns = {"NAME", "PRICE", "PROD. YEAR", "ENG. CAP"};
    
    public VehTableModel()
    {
        
    }
    
    public VehTableModel(List data, String[] columns)
    {
        this.data = data;
        this.columns = columns;
    }
    
    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }
    
    @Override
    public int getRowCount() {
        return  data.size();    
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public List<Vehicle> getData() {
        return data;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex) {
            case 0:
                return data.get(rowIndex).getName();
            case 1:
                return data.get(rowIndex).getPrice();
            case 2:
                return data.get(rowIndex).getProd_year();
            case 3:
                return data.get(rowIndex).getEng_capacity();

        }
        return null;
    }

    public void setData(List<Vehicle> data) {
        this.data = data;
        this.fireTableDataChanged();
    }

    public void addRow(Vehicle v){
        this.data.add(v);
        this.fireTableDataChanged();
        
    }
    
    public void removeRow(int vIndex)
    {
        this.data.remove(vIndex);
        this.fireTableDataChanged();
    }


   

}



