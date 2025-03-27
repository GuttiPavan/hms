package com.hms.hms.service;


import com.hms.hms.entity.Property;
import com.hms.hms.repository.PropertyRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

@Service

public class PdfService {


    private PropertyRepository propertyRepository;


    public void generatePdf(String filePath,Property property) {

       try {
           Document document = new Document();
           PdfWriter.getInstance(document, new FileOutputStream(filePath));

           document.open();

           //Text
           Font font = FontFactory.getFont(FontFactory.COURIER, 25, BaseColor.BLACK);
           Chunk chunk = new Chunk("Shiva ranjani hotel", font);
           document.add(chunk);

           //add image
//           Image img = Image.getInstance(path.toAbsolutePath().toString());
//           document.add(img);





           // Create a table with n columns
           PdfPTable table = new PdfPTable(2);

           // Add table headers
           table.addCell("Id");
           table.addCell(property.getId().toString());
           table.addCell("Name");
           table.addCell(property.getName());
           table.addCell("No of guest");
           table.addCell(property.getNo_of_guest().toString());
           table.addCell("price");
           table.addCell("price88");
           table.addCell("gst");
           table.addCell("gst8888");
           table.addCell("total");
           table.addCell("total888");


           // Add table rows
//           for (int i = 1; i <= 9; i++) {
//               table.addCell("Cell " + i);
//           }

           // Add the table to the document
           document.add(table);


           document.close();
       }catch (IOException | DocumentException e) {
           e.printStackTrace();
       }



        }


    }

