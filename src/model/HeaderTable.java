/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Amany.Atef
 */
public class HeaderTable extends AbstractTableModel{
    private ArrayList <InvoiceHeader> InvoiceHeadersList;
    private HeaderTable headerTable;
    private SimpleDateFormat NewDateFormate = new SimpleDateFormat("dd-MM-yyyy");    
    private String[] columns = {"Invoice Num", "Invoice Date", "Customer Name" ,"Total"};
    
//-------------------------------------------------------------------------------  
    public HeaderTable(ArrayList<InvoiceHeader> InvoiceHeadersList) {
        this.InvoiceHeadersList = InvoiceHeadersList;
    }
    
        public HeaderTable getHeaderTable() {
        return headerTable;
    }

    public void setHeaderTable(HeaderTable headerTable) {
        this.headerTable = headerTable;
    }
  //-------------------------------------------------------------------------------  
        public ArrayList <InvoiceHeader> getInvoiceHeadersList() {
        return InvoiceHeadersList;
    }

    public void setInvoiceHeadersList(ArrayList <InvoiceHeader> InvoiceHeadersList) {
        this.InvoiceHeadersList = InvoiceHeadersList;
    }
//-------------------------------------------------------------------------------    
    
    @Override
    public int getRowCount() {
        return InvoiceHeadersList.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;   
    }

    @Override
    public Object getValueAt(int RowIndex , int ColumnIndex) {
        InvoiceHeader inv = InvoiceHeadersList.get(RowIndex);
        switch(ColumnIndex){
            case 0: return inv.getNum();
            case 1: return NewDateFormate.format(inv.getDate());
            case 2: return inv.getCustomerName();
            case 3: return inv.getAllInvoiceLineTotal();
        }
        return "";
    }





    
}
