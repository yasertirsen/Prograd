package com.fyp.prograd.service;

import com.fyp.prograd.model.Image;
import com.fyp.prograd.model.Resume;
import com.fyp.prograd.repository.ApplicationRepository;
import com.fyp.prograd.repository.ImageRepository;
import com.fyp.prograd.repository.ResumeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class FileService {

    private final ImageRepository imageRepository;
    private final ResumeRepository resumeRepository;
    private final ApplicationRepository applicationRepository;

    public FileService(ImageRepository imageRepository, ResumeRepository resumeRepository,
                       ApplicationRepository applicationRepository) {
        this.imageRepository = imageRepository;
        this.resumeRepository = resumeRepository;
        this.applicationRepository = applicationRepository;
    }


    public Resume uploadResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public ResponseEntity<String> uploadImage(Image img) {
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

    public Image getImage(Long userId) {
        if(imageRepository.existsByUserId(userId)) {
            Image image = imageRepository.findByUserId(userId);
            image.setData(decompressBytes(image.getData()));
            return image;
        }
        return new Image();
    }

    public List<Resume> getCvs(Long userId) {
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

    public Resume getCv(Long applicationId) {
        Resume resume = applicationRepository.findByApplicationId(applicationId).getResume();
        resume.setData(decompressBytes(resume.getData()));
        return resume;
    }

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
