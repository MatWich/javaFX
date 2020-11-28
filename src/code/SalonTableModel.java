/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class SalonTableModel extends AbstractTableModel{

    List<String> data;
    String[] columns;
    
    public SalonTableModel(List<String> data, String[] columns) {
        this.data = data;
        this.columns = columns;
    }

    public List<String> getData() {
        return data;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setData(List<String> data) {
        this.data = data;
        this.fireTableDataChanged();
    }
//public SalonTableModel(List<String> list_of_names) { }

    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }

    @Override
    public int getRowCount() {
           return data.size();     
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        return data.get(rowIndex);
    }

    public void removeRow(int Index)
    {
        data.remove(Index);
        this.fireTableDataChanged();
    }

    public void addRow(String Index)
    {
        data.add(Index);
        this.fireTableDataChanged();
    }
}
