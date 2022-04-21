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
public class InvoiceAddNewHeaderDailog extends JDialog {

    private JTextField custNameTxtField;
    private JTextField invDateTxtField;
    private JLabel custNameJLabel;
    private JLabel invDateJLabel;
    private JButton okJButton;
    private JButton cancelJButton;

    public InvoiceAddNewHeaderDailog(Invoice invoice) {
        custNameJLabel = new JLabel("Customer Name");
        custNameTxtField = new JTextField(20);
        invDateJLabel = new JLabel("Invoice Date");
        invDateTxtField = new JTextField(20);
        
        okJButton = new JButton("OK");
        cancelJButton = new JButton("Cancel"); 
        okJButton.setActionCommand("CreateInvoiceHeaderOK");
        cancelJButton.setActionCommand("CreateInvoiceHeaderCancel");
        okJButton.addActionListener(invoice.getButtonsListener());
        cancelJButton.addActionListener(invoice.getButtonsListener());
        
        setLayout(new GridLayout(3, 2));
        
        add(invDateJLabel);
        add(invDateTxtField);
        add(custNameJLabel);
        add(custNameTxtField);
        add(okJButton);
        add(cancelJButton);
        pack();
        
    }

    public JTextField getCustNameField() {
        return custNameTxtField;
    }

    public JTextField getInvDateField() {
        return invDateTxtField;
    }
    
}

