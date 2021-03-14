package com.fyp.prograd.service;

import com.fyp.prograd.exceptions.JobNotFoundException;
import com.fyp.prograd.exceptions.ProgradException;
import com.fyp.prograd.model.Application;
import com.fyp.prograd.model.Position;
import com.fyp.prograd.repository.ApplicationRepository;
import com.fyp.prograd.repository.CompanyRepository;
import com.fyp.prograd.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final CompanyRepository companyRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository, CompanyRepository companyRepository,
                           ApplicationRepository applicationRepository) {
        this.positionRepository = positionRepository;
        this.companyRepository = companyRepository;
        this.applicationRepository = applicationRepository;
    }

    public Position add(Position position) {
        return positionRepository.save(position);
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }


    public Position findById(Long id) throws JobNotFoundException {
        if(!positionRepository.existsById(id))
            throw new JobNotFoundException("Position with id " + id + " was not found");
        return positionRepository.findByPositionId(id);
    }

    public Position update(Position position) {
        if(positionRepository.existsById(position.getPositionId())) {
            return positionRepository.save(position);
        }
        else
            throw new ProgradException("Position does not exist");
    }

    public List<Position> getCompanyPositions(Long id) {
        return positionRepository.findAllByCompany(companyRepository.findByCompanyId(id));
    }

    public ResponseEntity<String> apply(Application application) {
            applicationRepository.save(application);
            return new ResponseEntity<>("Application created successfully", HttpStatus.OK);
    }

    public List<Application> getApplicationsByPositionId(Long positionId) {
        return applicationRepository.findAllByPositionId(positionId);
    }

    public List<Application> getApplicationsByEmail(String email) {
        return applicationRepository.findAllByEmail(email);
    }

    public ResponseEntity<String> delete(Long positionId) {
        if(positionRepository.existsById(positionId)) {
            positionRepository.deleteById(positionId);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Could not find position with id " + positionId, HttpStatus.BAD_REQUEST);
    }
}
