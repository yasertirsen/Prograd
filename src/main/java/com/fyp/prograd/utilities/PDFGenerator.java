package com.fyp.prograd.utilities;

import com.fyp.prograd.model.Student;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PDFGenerator {

    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream studentCV(Student student) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
            Font regular = new Font(Font.FontFamily.TIMES_ROMAN, 12);



            Paragraph title = new Paragraph();
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(new Chunk(student.getFirstName() + " " + student.getSurname() + "\n", heading));
            title.add(new Chunk("Email: " + student.getEmail() + "\n", regular));
            title.add(new Chunk("Phone: " + student.getPhone() + "\n", regular));

            document.add(title);

            Paragraph education = new Paragraph();
            education.setAlignment(Element.ALIGN_LEFT);
            education.add(new Chunk("Education\n", heading));
            education.add(new Chunk(student.getProfile().getCourse().getUniversity() + "\n", regular));
            education.add(new Chunk(student.getProfile().getCourse().getName() + "\n", regular));

            document.add(education);

            document.close();
        }catch(DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
