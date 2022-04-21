/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Amany.Atef
 */
public class LinesTable extends AbstractTableModel {
    private ArrayList <InvoiceLine> InvoiceLinesList;

    public LinesTable(ArrayList<InvoiceLine> InvoiceLinesList) {
        this.InvoiceLinesList = InvoiceLinesList;
    }
    private LinesTable linesTable;
    private String[] columns = { "Item name", "Item price" ,"Item count" ,"Invoice Total"};

    @Override
    public int getRowCount() {
        System.out.println("InvoiceLinesList" +InvoiceLinesList.size());
        return InvoiceLinesList == null ? 0 : InvoiceLinesList.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (InvoiceLinesList== null ){return 0;}
        else{ InvoiceLine line = InvoiceLinesList.get(rowIndex);
        switch(columnIndex){
            //case 0: return line.getNum();
            case 0: return line.getItemName();
            case 1: return line.getItemPrice();
            case 2: return line.getItemCount();
            case 3: return line.getInvoiceLineTotal();
        }}
        return "";
    }
    
}
