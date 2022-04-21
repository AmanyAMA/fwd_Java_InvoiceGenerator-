/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Amany.Atef
 */
public class InvoiceLine {
   private int num;
   private String itemName;
   private Double itemPrice;
   private int itemCount;
   private InvoiceHeader header;  

    public InvoiceLine( String itemName, Double itemPrice, int itemCount, InvoiceHeader header) {
        //this.num = num;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }



    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
   
    public double getInvoiceLineTotal()
    {
        return itemPrice*itemCount;
    }

    @Override
    public String toString() {
        return   "InvNum= "+header.getNum() +", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount + ", header=" + header + '}';
    }
    
}
