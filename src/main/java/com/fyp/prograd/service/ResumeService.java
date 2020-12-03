package com.fyp.prograd.service;

import com.fyp.prograd.model.Resume;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.repository.ResumeRepository;
import com.fyp.prograd.repository.StudentRepository;
import com.fyp.prograd.utilities.PDFGenerator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, StudentRepository studentRepository) {
        this.resumeRepository = resumeRepository;
        this.studentRepository = studentRepository;
    }

    public Resume add(Resume resume) throws FileNotFoundException, DocumentException {
//        Document cv = new Document();
//        PdfWriter.getInstance(cv, new FileOutputStream("CV.pdf"));
//
//        cv.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//
//        Chunk chunk = new Chunk("Hello World", font);
//
//        cv.add(chunk);
//        cv.close();
//
//        resume.setResume(cv);
        return resume;
    }

    public List<Resume> getAll() {
        return resumeRepository.findAll();
    }

    public Resume update(Resume resume) {
        return null;
    }

    public ResponseEntity<InputStreamResource> getCv(String username) {
        Student student = studentRepository.findByUsername(username);

        ByteArrayInputStream bis = PDFGenerator.studentCV(student);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + username +" CV.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
