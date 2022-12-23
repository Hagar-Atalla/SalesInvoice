
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemsTable extends AbstractTableModel {

    private ArrayList<Line> Items;
    private String[] columns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public ItemsTable(ArrayList<Line> Items) 
    {
        this.Items = Items;
    }
     @Override
    public int getRowCount() 
    {
        return Items.size();
    }
    public ArrayList<Line> getLines() 
    {
        return Items;
    }
    @Override
    public int getColumnCount() 
    {
        return columns.length;
    }

    @Override
    public String getColumnName(int x) {
        return columns[x];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        if(columnIndex == 0)
            return Items.get(rowIndex).getInvoice().getinvoiceNum();
        else if(columnIndex == 1)
            return Items.get(rowIndex).getItem();
        else if(columnIndex == 2)
            return Items.get(rowIndex).getPrice();
        else if(columnIndex == 3)
            return Items.get(rowIndex).getCount();
        else if(columnIndex == 4)
            return Items.get(rowIndex).getLineTotal();
        else
            return "";
    }
    
}
