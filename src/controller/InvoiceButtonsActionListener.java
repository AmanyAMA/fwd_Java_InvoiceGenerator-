/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.LinesTable;
import view.Invoice;
import view.InvoiceAddNewHeaderDailog;
import view.InvoiceAddNewLineDialog;

/**
 *
 * @author Amany.Atef
 */
public class InvoiceButtonsActionListener implements ActionListener {
    private Invoice invoice;
    InvoiceAddNewHeaderDailog addHeaderDailog ;
    InvoiceAddNewLineDialog newLineDialog;
    private ArrayList <InvoiceHeader> InvoiceHeadersList;
    private ArrayList <InvoiceLine> InvoiceLinesList;

//-------------------------------------------------------------------------------
    public InvoiceButtonsActionListener(Invoice invoice) {
        this.invoice = invoice;
    }

    public ArrayList<InvoiceLine> getInvoiceLinesList() {
        return InvoiceLinesList;
    }

    public void setInvoiceLinesList(ArrayList<InvoiceLine> InvoiceLinesList) {
        this.InvoiceLinesList = InvoiceLinesList;
    }
    
    public ArrayList <InvoiceHeader> getInvoiceHeadersList() {
        return InvoiceHeadersList;
    }

    public void setInvoiceHeadersList(ArrayList <InvoiceHeader> InvoiceHeadersList) {
        this.InvoiceHeadersList = InvoiceHeadersList;
    }
//-------------------------------------------------------------------------------    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

           case "Create Invoice":
                createInvoiceBtn();
                break;

            case "Delete Invoice":
                deleteInvoiceBtn();
                break;

            case "New Line":
                createLineBtn();
                break;

            case "Delete Line":
                deleteLineBtn();
                break;
            case "CreateInvoiceHeaderOK":
                addNewInoviceHeader();
                break;
                
            case "CreateInvoiceHeaderCancel": 
                dismissCreateInvoiceHeaderDialog();
                break;
                
            case "InvoiceAddnewLineOK":
                AddInvoicenewLineDialod();
                break;
                
            case "InvoiceAddnewLineCancle":
                dismissInvoicenLineDialog();
                break;
        }
    }

    private void createInvoiceBtn() {
        addHeaderDailog = new InvoiceAddNewHeaderDailog(invoice);
        addHeaderDailog.setVisible(true);
        System.out.println("createInvoice");
    }

    private void deleteInvoiceBtn() {
        int InvSelectedRowIndex = invoice.getInvoiceHeaderTablejTable1().getSelectedRow();
        if (InvSelectedRowIndex != -1) {
            invoice.getInvoiceHeadersList().remove(InvSelectedRowIndex);
            invoice.getHeaderTable().fireTableDataChanged();
            invoice.getInvoiceLinejTable2().setModel(new LinesTable(new ArrayList<InvoiceLine>()));
            //invoice.setInvoiceLinesList(null);
            invoice.getInvoiceNumjLabel8().setText("");
            invoice.getCustomerNamejLabel5().setText("");
            invoice.getInvoiceDatejLabel7().setText("");
            invoice.getInvoiceTotaljLabel6().setText("");
        }
        System.out.println("deleteInvoice");
        
    }

    private void createLineBtn() {   
        newLineDialog = new InvoiceAddNewLineDialog(invoice);
        newLineDialog.setVisible(true);
        System.out.println("createInvoice");
    }

    private void deleteLineBtn() {
         int InvSelectedLine= invoice.getInvoiceLinejTable2().getSelectedRow();
         int InvSelectedHeader= invoice.getInvoiceHeaderTablejTable1().getSelectedRow();

         if (InvSelectedLine != -1) 
        {
            invoice.getInvoiceLinesList().remove(InvSelectedLine);
            LinesTable lineTable= (LinesTable) invoice.getInvoiceLinejTable2().getModel();
            lineTable.fireTableDataChanged();
            invoice.getInvoiceTotaljLabel6().setText("" + invoice.getInvoiceHeadersList().get(InvSelectedHeader).getAllInvoiceLineTotal());       
            invoice.getHeaderTable().fireTableDataChanged();
            invoice.getInvoiceHeaderTablejTable1().setRowSelectionInterval(InvSelectedHeader,InvSelectedHeader);
            /*
        int selectedLineIndex = frame.getInvLTbl().getSelectedRow();
        int selectedInvoiceIndex = frame.getInvHTbl().getSelectedRow();
        if (selectedLineIndex != -1) {
            frame.getLinesArray().remove(selectedLineIndex);
            InvoiceLineTableModel lineTableModel = (InvoiceLineTableModel) frame.getInvLTbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvTotalIbl().setText("" + frame.getInvoicesArray().get(selectedInvoiceIndex).getInvoiceTotal());
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvHTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }*/
        }

    }

    private void dismissCreateInvoiceHeaderDialog() {
        addHeaderDailog.setVisible(false);
        addHeaderDailog.dispose();
        addHeaderDailog=null;
    }

    private void addNewInoviceHeader() {
        String InvoiceDate = addHeaderDailog.getInvDateField().getText();
        Date date =new Date();
        try {
            date= invoice.NewDateFormate.parse(InvoiceDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(invoice, "System Couldn't Parse the Entered Date", "Wrong date Formate", JOptionPane.ERROR_MESSAGE);
            
        }
        
        String CustomerName = addHeaderDailog.getCustNameField().getText();
        int InvoiceNo =0;
        for(InvoiceHeader inv:invoice.getInvoiceHeadersList())
        {
            if(inv.getNum()>InvoiceNo) InvoiceNo=inv.getNum();
        }
        InvoiceNo++;
        InvoiceHeader invHeader =new InvoiceHeader(InvoiceNo,CustomerName,date);
        invoice.getInvoiceHeadersList().add(invHeader);
        invoice.getHeaderTable().fireTableDataChanged();
        addHeaderDailog.setVisible(false);
        addHeaderDailog.dispose();
        addHeaderDailog=null;
    }

    private void AddInvoicenewLineDialod() {
        String ItemName= newLineDialog.getItemNameField().getText();
        String ItemPrice =newLineDialog.getItemPriceField().getText();
        String ItemCount =newLineDialog.getItemCountField().getText();
        int itemcount=1;
        double itemprice=1.0;
        try{
        itemcount = Integer.parseInt(ItemCount);
        }catch(NumberFormatException ex)
        {
            JOptionPane.showConfirmDialog(invoice, "System couldn't parse the entered number" , "invalid formate", JOptionPane.ERROR_MESSAGE);
        }
        
        try{
         itemprice =Double.parseDouble(ItemPrice);
        }catch(NumberFormatException ex)
        {
           JOptionPane.showConfirmDialog(invoice, "System couldn't parse the entered number" , "invalid formate", JOptionPane.ERROR_MESSAGE);
        }
        int InvSelectedHeader = invoice.getInvoiceHeaderTablejTable1().getSelectedRow();
        if (InvSelectedHeader != -1) 
        {
            InvoiceHeader invHeader =invoice.getInvoiceHeadersList().get(InvSelectedHeader);            
            InvoiceLine line = new InvoiceLine(ItemName, itemprice, itemcount, invHeader);
            invoice.getInvoiceLinesList().add(line);

            LinesTable lineTable= (LinesTable) invoice.getInvoiceLinejTable2().getModel();
            lineTable.fireTableDataChanged();
            invoice.getHeaderTable().fireTableDataChanged();
        }
        invoice.getInvoiceHeaderTablejTable1().setRowSelectionInterval(InvSelectedHeader, InvSelectedHeader);
        newLineDialog.dispose();
        newLineDialog = null;
    }           
    private void dismissInvoicenLineDialog() {
        newLineDialog.setVisible(false);
        newLineDialog.dispose();
        newLineDialog=null;
        System.out.println("deleteLine");
    }


  
}
