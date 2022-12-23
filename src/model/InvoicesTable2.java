package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoicesTable2 extends AbstractTableModel 
{
    private ArrayList<Invoice> invoices;
    private String[] columns = {"No.", "Date", "Customer", "Total"};
    
    public InvoicesTable2(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        Invoice invoice = invoices.get(rowIndex);
        if(columnIndex == 0)
            return invoices.get(rowIndex).getinvoiceNum();
        else if(columnIndex == 1)
            return invoices.get(rowIndex).getinvoiceDate();
        else if(columnIndex == 2)
            return invoices.get(rowIndex).getcustomerName();
        else if(columnIndex == 3)
            return invoices.get(rowIndex).getInvoiceTotal();
        else
            return "";
    }
}
