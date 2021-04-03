package com.fyp.prograd.controller;

import com.fyp.prograd.exceptions.UserNotFoundException;
import com.fyp.prograd.model.Image;
import com.fyp.prograd.model.Resume;
import com.fyp.prograd.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4202")
@RequestMapping( "/api/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/saveCv")
    public Resume uploadResume(@RequestBody Resume resume) {
        return fileService.uploadResume(resume);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadImage(@RequestBody Image img) {
        return fileService.uploadImage(img);
    }

    @GetMapping("/getImage")
    public Image getImage(@RequestParam Long userId) {
        return fileService.getImage(userId);
    }

    @GetMapping("/getAllCvs/{userId}")
    public List<Resume> getCvs(@PathVariable Long userId) {
        return fileService.getCvs(userId);
    }

    @GetMapping("/getCv")
    public Resume getCv(@RequestParam Long applicationId) {
        return fileService.getCv(applicationId);
    }

    @PutMapping("/updateCv")
    public Resume updateCv(@RequestBody Resume resume) throws UserNotFoundException {
        return fileService.updateCV(resume);
    }
}
