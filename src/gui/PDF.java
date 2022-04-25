/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Reservation;
import Services.ServiceReservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author USER
 */
public class PDF {
     public void liste_Reservation() throws FileNotFoundException, DocumentException {

         ServiceReservation seRes = new ServiceReservation();
        String message = "\n\n Voici votre liste des Reserevation \n\n";

        String file_name = "src/resources/MyResrvation.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Reservation> res = seRes.AfficherResObj();
        PdfPTable table = new PdfPTable(4);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("Jeux Reservé"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Date Reservation"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("Heure de Debut "));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Coach "));
        table.addCell(cl3);
        
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < res.size(); i++) {
            
            table.addCell("" + res.get(i).getGuide().getTitre());
            table.addCell("" + res.get(i).getDate_res());
            table.addCell("" + res.get(i).getHeure_debut());
            table.addCell("" + res.get(i).getCoach().getNom());

        }
        document.add(table);

        document.close();

    }
}
