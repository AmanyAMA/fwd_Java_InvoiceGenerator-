/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.HeaderTable;
import model.InvoiceHeader;
import model.InvoiceLine;
import view.Invoice;

/**
 *
 * @author Amany.Atef
 */
public class InvoiceMenuActionListener implements ActionListener {
    private Invoice invoice;
    private static  SimpleDateFormat NewDateFormate = new SimpleDateFormat("dd-MM-yyyy");


    public InvoiceMenuActionListener(Invoice invoice) {
        this.invoice = invoice;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
        case "LoadFiles":
            {
                try {
                    loadFiles();
                } catch (IOException ex) {
                    Logger.getLogger(InvoiceMenuActionListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(InvoiceMenuActionListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            break;


        case "SaveFiles":
            saveFiles();
            break;
            
        }
    }

    private void loadFiles() throws IOException, ParseException {
        System.out.println("loadFiles");
         JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int SelectedBtn = chooser.showOpenDialog(invoice);
        if (SelectedBtn==JFileChooser.APPROVE_OPTION){
         File []files = chooser.getSelectedFiles();
         ArrayList <InvoiceLine> invLineList= null;
         ArrayList <InvoiceHeader> invHeaderList= null;
        for(File file : files){
             if(file.isFile()){
                 Path p =Paths.get(file.getAbsolutePath());
                 List<String> lines = Files.readAllLines(p);
                 if (p.endsWith("InvoiceHeader.csv")){
                      invHeaderList = new ArrayList <>();
                       for(String line : lines){
                        String[]headerItems= line.split(",");
                        String no = headerItems[0];
                        String date = headerItems[1];
                        String name = headerItems[2];
                        int No = Integer.parseInt(no);
                        Date invDate= NewDateFormate.parse(date);
                        InvoiceHeader header=new InvoiceHeader(No,name,invDate);
                        invHeaderList.add(header);
                    }
                       invoice.setInvoiceHeadersList(invHeaderList);
                 }
                 else if(p.endsWith("InvoiceLine.csv")){
                     invLineList= new ArrayList<>();
                     for(String line : lines){
                        String[]items= line.split(",");
                        String no = items[0];                      
                        String itemName = items[1];
                        String itemPrice = items[2];
                        String itemCount = items[3];
                        int No = Integer.parseInt(no);
                        double ItemPrice =Double.parseDouble(itemPrice);
                        int ItemCount = Integer.parseInt(itemCount);
                        InvoiceHeader MatchedInvoiceHeader=invoice.getMatchedHeaderByNo(No);
                        InvoiceLine invline = new InvoiceLine(itemName ,ItemPrice ,ItemCount, MatchedInvoiceHeader);
                        MatchedInvoiceHeader.getLines().add(invline);
                         System.out.println(invline);

                     }
               
                 }    
             
             }
        HeaderTable headerTable = new HeaderTable(invHeaderList);
        invoice.setInvoiceHeadersList(invHeaderList);
        invoice.setHeaderTable(headerTable);
        invoice.getInvoiceHeaderTablejTable1().setModel(headerTable);
           
        }
        
        }

    }

    private void saveFiles() {
        ArrayList<InvoiceHeader> InvoiceHeaderList = invoice.getInvoiceHeadersList();
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showSaveDialog(invoice);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter headerFileWriter = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (InvoiceHeader invoice : InvoiceHeaderList) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (InvoiceLine line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fc.showSaveDialog(invoice);
                File lineFile = fc.getSelectedFile();
                FileWriter LineFileWriter = new FileWriter(lineFile);
                headerFileWriter.write(headers);
                LineFileWriter.write(lines);
                headerFileWriter.close();
                LineFileWriter.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(invoice, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("saveFiles");

    }
        
 }
  

