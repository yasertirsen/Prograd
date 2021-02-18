package com.fyp.prograd.controller;

import com.fyp.prograd.model.Image;
import com.fyp.prograd.model.Resume;
import com.fyp.prograd.repository.ImageRepository;
import com.fyp.prograd.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "http://localhost:4202")
@RequestMapping( "/api/files")
public class FileController {

    private final ImageRepository imageRepository;
    private final ResumeRepository resumeRepository;

    @Autowired
    public FileController(ImageRepository imageRepository, ResumeRepository resumeRepository) {
        this.imageRepository = imageRepository;
        this.resumeRepository = resumeRepository;
    }

    @PostMapping(value = "/saveCv")
    public ResponseEntity<String> uploadImage(@RequestBody Resume resume) {
        resumeRepository.save(resume);
        return new ResponseEntity<>("saved successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadImage(@RequestBody Image img) {
        if(!imageRepository.existsByUserId(img.getUserId())) {
            imageRepository.save(img);
            return new ResponseEntity<>("saved successfully", HttpStatus.OK);
        }
        else {
            imageRepository.delete(imageRepository.findByUserId(img.getUserId()));
            imageRepository.save(img);
            return new ResponseEntity<>("updated successfully", HttpStatus.OK);
        }

    }

    @GetMapping("/getImage")
    public Image getImage(@RequestParam Long userId) {
        if(imageRepository.existsByUserId(userId)) {
            Image image = imageRepository.findByUserId(userId);
            image.setData(decompressBytes(image.getData()));
            return image;
        }
        //change to throw error
        return new Image();
    }

    @GetMapping("/getCvs")
    public List<Resume> getCvs(@RequestParam Long userId) {
        if(resumeRepository.existsByStudentId(userId)) {
            List<Resume> resumes = resumeRepository.findAllByStudentId(userId);
            for(Resume cv : resumes) {
                cv.setData(decompressBytes(cv.getData()));
            }
            return resumes;
        }
        else
            return new ArrayList<>();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
            ioe.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
