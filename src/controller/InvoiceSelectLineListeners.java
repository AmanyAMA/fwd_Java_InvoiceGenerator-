/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.LinesTable;
import view.Invoice;

/**
 *
 * @author Amany.Atef
 */
public class InvoiceSelectLineListeners implements ListSelectionListener {
    private Invoice invoice;
    public InvoiceSelectLineListeners(Invoice invoice) {
        this.invoice = invoice;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int InvSelectedRowIndex= invoice.getInvoiceHeaderTablejTable1().getSelectedRow();
        System.out.println("LineSelected " + InvSelectedRowIndex);
        if (InvSelectedRowIndex != -1) {
        InvoiceHeader selectedinvHeader= invoice.getInvoiceHeadersList().get(InvSelectedRowIndex);
        ArrayList<InvoiceLine> lines= selectedinvHeader.getLines();
        LinesTable lineTable = new  LinesTable(lines);
        invoice.setInvoiceLinesList(lines);
        invoice.getInvoiceLinejTable2().setModel(lineTable);
        invoice.getInvoiceNumjLabel8().setText(""+selectedinvHeader.getNum());
        invoice.getInvoiceDatejLabel7().setText(invoice.NewDateFormate.format(selectedinvHeader.getDate()));
        invoice.getCustomerNamejLabel5().setText(selectedinvHeader.getCustomerName());
        invoice.getInvoiceTotaljLabel6().setText(""+selectedinvHeader.getAllInvoiceLineTotal());
        }
    }
    
}
