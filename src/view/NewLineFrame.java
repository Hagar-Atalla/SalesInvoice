package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewLineFrame extends JDialog{
    private JTextField itemNameField;
    private JTextField CountField;
    private JTextField itemPriceField;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton createItemButton;
    private JButton cancelItemButton;
    
    public NewLineFrame(MainFrame frame) {
        itemNameField = new JTextField(20);
        itemNameLabel = new JLabel("Name");
        
        CountField = new JTextField(20);
        itemCountLabel = new JLabel("Count");
        
        itemPriceField = new JTextField(20);
        itemPriceLabel = new JLabel("Price");
        
        createItemButton = new JButton("Create Item");
        cancelItemButton = new JButton("Cancel");
        
        createItemButton.setActionCommand("create Item");
        createItemButton.addActionListener(frame.getEngine());
        cancelItemButton.setActionCommand("cancelNewItem");
        cancelItemButton.addActionListener(frame.getEngine());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel);
        add(itemNameField);
        add(itemCountLabel);
        add(CountField);
        add(itemPriceLabel);
        add(itemPriceField);
        add(createItemButton);
        add(cancelItemButton);
        
        pack();
        this.repaint();
        this.revalidate();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }
    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    public JTextField getCountField() {
        return CountField;
    }

}
