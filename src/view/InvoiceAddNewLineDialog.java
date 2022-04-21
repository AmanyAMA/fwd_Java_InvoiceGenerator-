/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Amany.Atef
 */
public class InvoiceAddNewLineDialog extends JDialog{
    private JTextField itemNameTxtField;
    private JTextField itemCountTxtField;
    private JTextField itemPriceTxtField;
    private JLabel itemNameJLabel;
    private JLabel itemCountJLabel;
    private JLabel itemPriceJLabel;
    private JButton okJButton;
    private JButton cancelJButton;
    
    public InvoiceAddNewLineDialog(Invoice invoice) {
        itemNameTxtField = new JTextField(20);
        itemNameJLabel = new JLabel("Item Name");
        
        itemCountTxtField = new JTextField(20);
        itemCountJLabel = new JLabel("Item Count");
        
        itemPriceTxtField = new JTextField(20);
        itemPriceJLabel = new JLabel("Item Price");
        
        okJButton = new JButton("OK");
        cancelJButton = new JButton("Cancel");
        
        okJButton.setActionCommand("InvoiceAddnewLineOK");
        cancelJButton.setActionCommand("InvoiceAddnewLineCancel");
        
        okJButton.addActionListener(invoice.getButtonsListener());
        cancelJButton.addActionListener(invoice.getButtonsListener());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameJLabel);
        add(itemNameTxtField);
        add(itemCountJLabel);
        add(itemCountTxtField);
        add(itemPriceJLabel);
        add(itemPriceTxtField);
        add(okJButton);
        add(cancelJButton);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameTxtField;
    }

    public JTextField getItemCountField() {
        return itemCountTxtField;
    }

    public JTextField getItemPriceField() {
        return itemPriceTxtField;
    }
}

