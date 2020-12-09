package com.fyp.prograd.utilities;

import com.fyp.prograd.model.Student;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

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

            Font heading = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.DARK_GRAY);
            Font regular = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.DARK_GRAY);
            Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.DARK_GRAY);
            LineSeparator lsHeading = new LineSeparator();
            LineSeparator lsPar = new LineSeparator();
            lsHeading.setOffset(10);
            lsHeading.setLineWidth(2);
            lsPar.setLineWidth(0.5f);
            lsPar.setLineColor(BaseColor.LIGHT_GRAY);

            Paragraph title = new Paragraph();
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(new Chunk(student.getFirstName() + " " + student.getSurname() + "\n", heading));
            title.add(new Chunk("Email: " + student.getEmail() + "\n", regular));
            title.add(new Chunk("Phone: " + student.getPhone() + "\n", regular));
            title.add(lsPar);
            document.add(title);

            Paragraph summary = new Paragraph("Summary\n", heading);
            summary.setSpacingBefore(20);
            summary.add(lsHeading);
            document.add(summary);
            Paragraph summaryContent = new Paragraph();
            summaryContent.setSpacingBefore(8);
            summaryContent.add(new Chunk(student.getFirstName() + "\n", regular));
            summaryContent.add(lsPar);
            document.add(summaryContent);

            Paragraph education = new Paragraph("Education\n", heading);
            education.setSpacingBefore(20);
            education.add(lsHeading);
            document.add(education);
            Paragraph educationContent = new Paragraph();
            educationContent.setSpacingBefore(8);
            educationContent.add(new Chunk(student.getProfile().getCourse().getName() + "\n", bold));
            educationContent.add(new Chunk(student.getProfile().getCourse().getUniversity() + "\n", regular));
            educationContent.add(lsPar);
            document.add(educationContent);

            Paragraph projects = new Paragraph();
            projects.setSpacingBefore(20);
            projects.add(new Chunk("Projects\n", heading));
            projects.add(lsHeading);
            document.add(projects);
            Paragraph projectsContent = new Paragraph();
            projectsContent.setSpacingBefore(8);
            List projectsList = new List();
            projectsList.add(new ListItem("PROJECT 1", regular));
            projectsList.add(new ListItem("PROJECT 2", regular));
            projectsList.add(new ListItem("PROJECT 3", regular));
            projectsContent.add(projectsList);
            projectsContent.add(lsPar);
            document.add(projectsContent);

            Paragraph skills = new Paragraph();
            skills.setSpacingBefore(20);
            skills.add(new Chunk("Skills\n", heading));
            skills.add(lsHeading);
            document.add(skills);
            Paragraph skillsContent = new Paragraph();
            skillsContent.setSpacingBefore(8);
            List skillsList = new List();
            skillsList.add(new ListItem("SKILL 1", regular));
            skillsList.add(new ListItem("SKILL 2", regular));
            skillsList.add(new ListItem("SKILL 3", regular));
            skillsContent.add(skillsList);
            skills.add(lsPar);
            document.add(skillsContent);

            document.close();
        }catch(DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
