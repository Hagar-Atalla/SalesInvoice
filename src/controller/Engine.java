package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import model.Invoice;
import model.InvoicesTable2;
import model.Line;
import model.ItemsTable;
import view.NewInvoiceFrame;
import view.MainFrame;
import view.NewLineFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Engine implements ActionListener, ListSelectionListener 
{
    private MainFrame frame;
    private NewLineFrame newItemFrame;
    private NewInvoiceFrame newInvoiceFrame;
    
    public Engine(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if("Create New Invoice".equals(e.getActionCommand()))
        {
            createNewInvoice();
        }
        if("Delete Invoice".equals(e.getActionCommand()))
        {
            deleteInvoice();
        }
        if("Create New Item".equals(e.getActionCommand()))
        {
        }
        if("Load File".equals(e.getActionCommand()))
        {   
            try {
                loadFile();
            } catch (IOException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("Save File".equals(e.getActionCommand()))
        {
            try {
                saveFile();
            } catch (IOException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("create Invoice".equals(e.getActionCommand()))
        {
                createInvoice();
        }
        if("create Invoice Cancelation".equals(e.getActionCommand()))
        {
                createInvoiceCancelation();
        }
        if("delete Item".equals(e.getActionCommand()))
        {
            deleteItem();
        }
        if("Create New Item".equals(e.getActionCommand()))
        {
            createNewItem();
        }
        if("create Item".equals(e.getActionCommand()))
        {
            createNewItemConfirm();
        }
        if("cancelNewItem".equals(e.getActionCommand()))
        {
            cancelNewItem();
        }
    }
     private void deleteItem()
     {

        if (frame.getItemsTable().getSelectedRow() >= 0) {
            ItemsTable itemsTable = (ItemsTable) frame.getItemsTable().getModel();
            itemsTable.getLines().remove(frame.getItemsTable().getSelectedRow());
            itemsTable.fireTableDataChanged();
            frame.getInvoicesTable().fireTableDataChanged();
        }
    }
    private void cancelNewItem() 
    {
        newItemFrame.setVisible(false);
        newItemFrame.dispose();
        newItemFrame = null;
    }
     private void createNewItem() 
     {
        newItemFrame = new NewLineFrame(frame);
        newItemFrame.setVisible(true);
    }
     
     private void createNewItemConfirm() 
     {
        String item = newItemFrame.getItemNameField().getText();
        String priceStr = newItemFrame.getItemPriceField().getText();
        int count = Integer.parseInt(newItemFrame.getCountField().getText());
        int price = Integer.parseInt(priceStr);
        if (frame.getInvoiceTable().getSelectedRow() >= 0) 
        {
            Invoice invoice = frame.getInvoices().get(frame.getInvoiceTable().getSelectedRow());
            Line newLine = new Line(item, price, count, invoice);
            invoice.getinvoicelines().add(newLine);
            ItemsTable linesTable = (ItemsTable) frame.getItemsTable().getModel();
            linesTable.fireTableDataChanged();
            frame.getInvoicesTable().fireTableDataChanged();
        }
        newItemFrame.setVisible(false);
        newItemFrame.dispose();
        newItemFrame = null;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if (frame.getInvoiceTable().getSelectedRow() >= 0) 
        {
            Invoice currentInvoice = frame.getInvoices().get(frame.getInvoiceTable().getSelectedRow());
            frame.getInvoiceNumLabel().setText("" + currentInvoice.getinvoiceNum());
            frame.getInvoiceDateLabel().setText(currentInvoice.getinvoiceDate());
            frame.getCustomerNameLabel().setText(currentInvoice.getcustomerName());
            frame.getInvoiceTotalLabel().setText("" + currentInvoice.getInvoiceTotal());
            ItemsTable linesTableModel = new ItemsTable(currentInvoice.getinvoicelines());
            frame.getItemsTable().setModel(linesTableModel);
            linesTableModel.fireTableDataChanged();
        }
    }
//done
    private void loadFile() throws IOException 
    {
         JFileChooser fileChooser = new JFileChooser();
        int showOpenDialog = fileChooser.showOpenDialog(frame);
        if(showOpenDialog == JFileChooser.APPROVE_OPTION)
        {
            ArrayList<Invoice> invoicesArray = new ArrayList<>();
            File file = fileChooser.getSelectedFile();
            Path filePath = Paths.get(file.getAbsolutePath());
            List<String> filePaths = (ArrayList<String>) Files.readAllLines(filePath);
            for(int i = 0;i<filePaths.size();i++)
            {
                String[] info = filePaths.get(i).split(",");
                int invoiceNum = Integer.parseInt(info[0]);
                String invoiceDate = info[1];
                String customerName = info[2];
                Invoice invoice = new Invoice(invoiceNum,invoiceDate,customerName);
                invoicesArray.add(invoice);
            }
            showOpenDialog = fileChooser.showOpenDialog(frame);
            if(showOpenDialog == JFileChooser.APPROVE_OPTION)
            {
                File linefile = fileChooser.getSelectedFile();
                Path linePath = Paths.get(linefile.getAbsolutePath());
                List<String> lineLines = Files.readAllLines(linePath);
                for(int i = 0;i<lineLines.size();i++)
                {
                    String[] lineInfo = lineLines.get(i).split(",");
                    int invoiceNumber =  Integer.parseInt(lineInfo[0]);
                    String itemName = lineInfo[1];
                    int itemprice =  Integer.parseInt(lineInfo[2]);
                    int count =  Integer.parseInt(lineInfo[3]);
                    Invoice inv = null;
                    for(int j = 0;j<invoicesArray.size();j++)
                    {
                        if(invoicesArray.get(j).getinvoiceNum() == invoiceNumber)
                        {
                            inv = invoicesArray.get(j);
                            break;
                        }
                    }
                    Line invoiceline = new Line(itemName,itemprice,count,inv);
                    //add each line to each invoice by comparing its invoice number  
                    inv.getinvoicelines().add(invoiceline);
                }
            }
                frame.setInvoices(invoicesArray);
                InvoicesTable2 invoicesTableModel = new InvoicesTable2(invoicesArray);
                frame.setInvoicesTableModel(invoicesTableModel);
                frame.getInvoiceTable().setModel(invoicesTableModel);
                frame.getInvoicesTable().fireTableDataChanged();
            }
    }

    private void saveFile() throws IOException 
    {
        ArrayList<Invoice> invoices = frame.getInvoices();
        String headers = "";
        String lines = "";
        for(int i =0;i<invoices.size();i++) {
            String invoiceCSV = invoices.get(i).getAsCSV();
            headers = headers + invoiceCSV + "\n";

            for (int j = 0;j<invoices.get(i).getinvoicelines().size();j++ ) {
                String lineCSV = invoices.get(i).getinvoicelines().get(j).getAsCSV();
                lines = lines + lineCSV + "\n";
            }
        }
        System.out.println("Check point");
        JFileChooser fileChooser = new JFileChooser();
        int fileChooserint = fileChooser.showSaveDialog(frame);
        if (fileChooserint == JFileChooser.APPROVE_OPTION) 
        {
            File headerFile = fileChooser.getSelectedFile();
            FileWriter fileWriter = new FileWriter(headerFile);
            fileWriter.write(headers);
            fileWriter.flush();
            fileWriter.close();
            fileChooserint = fileChooser.showSaveDialog(frame);
            if (fileChooserint == JFileChooser.APPROVE_OPTION) 
            {
                File line = fileChooser.getSelectedFile();
                FileWriter fileWriter2 = new FileWriter(line);
                fileWriter2.write(lines);
                fileWriter2.flush();
                fileWriter2.close();
            }
        }
    }

    private void createNewInvoice() 
    {
        newInvoiceFrame = new NewInvoiceFrame(frame);
        newInvoiceFrame.setVisible(true);
    }

    private void deleteInvoice() 
    {
        if (frame.getInvoiceTable().getSelectedRow() >= 0) 
        {
            frame.getInvoices().remove(frame.getInvoiceTable().getSelectedRow());
            frame.getInvoicesTable().fireTableDataChanged();
        }
    }

    private void createInvoiceCancelation() 
    {
        newInvoiceFrame.setVisible(false);
        newInvoiceFrame.dispose();
        newInvoiceFrame = null;
    }

    private void createInvoice() 
    {
        String date = newInvoiceFrame.getDateTextField().getText();
        String customerName = newInvoiceFrame.getNameTextField().getText();
        int num = frame.getNewInvoiceNum();
            String[] dateParts = date.split("/");  
            if (dateParts.length < 3) 
            {
                JOptionPane.showMessageDialog(frame, "Wrong date format Enter Date dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else 
            {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) 
                {
                    JOptionPane.showMessageDialog(frame, "Wrong date format Enter Date dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                else 
                {
                    Invoice newInvoice = new Invoice(num, date, customerName);
                    frame.getInvoices().add(newInvoice);
                    frame.getInvoicesTable().fireTableDataChanged();
                    newInvoiceFrame.setVisible(false);
                    newInvoiceFrame.dispose();
                    newInvoiceFrame = null;
                }
            }
    }
       
    

}
