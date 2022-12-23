
package model;

import java.util.ArrayList;

public class Invoice 
{
    private int invoiceNum;
    private String invoiceDate;
    private String customerName;
    private ArrayList<Line> invoicelines;
    
    public Invoice() 
    {
    }

    public Invoice(int num, String date, String customer) {
        this.invoiceNum = num;
        this.invoiceDate = date;
        this.customerName = customer;
    }

    public double getInvoiceTotal() 
    {
        double total = 0;
        for(int i =0;i< getinvoicelines().size();i++)
        {
             total += getinvoicelines().get(i).getLineTotal();
        }
        return total;
    }
    
    public ArrayList<Line> getinvoicelines() 
    {
        if (invoicelines == null) 
        {
            invoicelines = new ArrayList<>();
        }
        return invoicelines;
    }

    public String getcustomerName() 
    {
        return customerName;
    }

    public void setcustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public int getinvoiceNum() {
        return invoiceNum;
    }

    public void setinvoiceNum(int invoiceNum) 
    {
        this.invoiceNum = invoiceNum;
    }

    public String getinvoiceDate() 
    {
        return invoiceDate;
    }

    public void setinvoiceDate(String invoiceDate) 
    {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() 
    {
        return "Invoice{" + "num=" + invoiceNum + ", date=" + invoiceDate + ", customer=" + customerName + '}';
    }
    
    public String getAsCSV() 
    {
        return invoiceNum + "," + invoiceDate + "," + customerName;
    }
    
}
