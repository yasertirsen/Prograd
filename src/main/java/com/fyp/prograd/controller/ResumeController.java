package com.fyp.prograd.controller;

import com.fyp.prograd.model.Resume;
import com.fyp.prograd.service.ResumeService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    private final String AUTH_TOKEN = "x-api-key";

    @PostMapping(value = "/add", consumes = "application/json")
    public Resume add(@RequestBody Resume resume) throws FileNotFoundException, DocumentException {
        return resumeService.add(resume);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Resume> getAll() {
        return resumeService.getAll();
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Resume update(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody Resume resume) {
        return resumeService.update(resume);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<InputStreamResource> getCv(@RequestHeader(AUTH_TOKEN) String bearerToken, @PathVariable String username) {
        return resumeService.getCv(username);
    }
}
