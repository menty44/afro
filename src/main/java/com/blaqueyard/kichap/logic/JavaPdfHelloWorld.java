package com.blaqueyard.kichap.logic;


/**
 * Created by admin on 11/6/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//import static sun.plugin.javascript.navig.JSType.Document;

public class JavaPdfHelloWorld
{
    public static void main(String[] args)
    {
        Document document = new Document();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("sancom.pdf"));
            document.open();
            document.add(new Paragraph("A Hello World PDF document."));
            document.close();
            writer.close();
            
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
