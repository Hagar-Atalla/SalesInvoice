package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class NewInvoiceFrame extends JDialog 
{
    private JTextField DateTextField;
    private JLabel NameLabel;
    private JTextField NameTextField;
    private JLabel DateLabel;
    private JButton createInvoiceButton;
    private JButton cancelInvoiceButton;

    public NewInvoiceFrame(MainFrame frame) {
        NameLabel = new JLabel("Customer Name:");
        DateLabel = new JLabel("Invoice Date:");
        NameTextField = new JTextField(20);
        DateTextField = new JTextField(20);
        createInvoiceButton = new JButton("Create Invoice");
        cancelInvoiceButton = new JButton("Cancel");
        
        createInvoiceButton.setActionCommand("create Invoice");
        createInvoiceButton.addActionListener(frame.getEngine());
        cancelInvoiceButton.setActionCommand("create Invoice Cancelation");
        cancelInvoiceButton.addActionListener(frame.getEngine());
        
        setLayout(new GridLayout(3, 2));
        add(DateLabel);
        add(DateTextField);
        add(NameLabel);
        add(NameTextField);
        add(createInvoiceButton);
        add(cancelInvoiceButton);
        pack();
        this.repaint();
        this.revalidate();
    }

    public JTextField getNameTextField() {
        return NameTextField;
    }

    public JTextField getDateTextField() {
        return DateTextField;
    }
    
}
