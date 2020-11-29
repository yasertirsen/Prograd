package com.fyp.prograd.service;

import com.fyp.prograd.exceptions.JobNotFoundException;
import com.fyp.prograd.model.Position;
import com.fyp.prograd.repository.CompanyRepository;
import com.fyp.prograd.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position add(Position position) {
        return positionRepository.save(position);
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }


    public Position findById(Long id) throws JobNotFoundException {
        if(positionRepository.existsById(id))
            throw new JobNotFoundException("Position with id " + id + " was not found");
        return positionRepository.findByPositionId(id);
    }

    public Position update(Position position) {
        return positionRepository.save(position);
    }
}
