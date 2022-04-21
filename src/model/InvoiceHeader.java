/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Amany.Atef
 */
public class InvoiceHeader {
    private int num;
    private String customerName;
    private Date date;
    private ArrayList<InvoiceLine> lines;
    private DateFormat NewDateFormate = new SimpleDateFormat("dd-MM-yyyy");


    public InvoiceHeader(int num, String customerName, Date date) {
        this.num = num;
        this.customerName = customerName;
        this.date = date;
       // this.lines = lines;, ArrayList<InvoiceLine> lines
    }
    

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null){
         lines= new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public double getAllInvoiceLineTotal()
    {
        double total=0;
        for(int i=0; i<getLines().size();i++){
        total+=lines.get(i).getInvoiceLineTotal();  
        }
        return total;
    }

    @Override
    public String toString() {
        return  "num=" + num +", date=" + NewDateFormate.format(date) +  ", customerName=" + customerName ;
    }
    
}
