package com.fyp.prograd.controller;

import com.fyp.prograd.exceptions.JobNotFoundException;
import com.fyp.prograd.model.Application;
import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.Position;
import com.fyp.prograd.model.Resume;
import com.fyp.prograd.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Position add(@Valid @RequestBody Position position){
        return positionService.add(position);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Position> getAll() {
        return positionService.getAll();
    }

    @GetMapping(value = "/findById", produces = "application/json")
    public Position findById(@RequestParam Long id) throws JobNotFoundException {
        return positionService.findById(id);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Position update(@RequestBody Position position) {
        return positionService.update(position);
    }

    @GetMapping(value= "/getCompanyPositions/{companyId}", produces = "application/json")
    public List<Position> getCompanyPositions(@PathVariable Long companyId) {
        return positionService.getCompanyPositions(companyId);
    }

    @PostMapping("/apply")
    public ResponseEntity<String> apply(@RequestBody Application application) {
        return positionService.apply(application);
    }

    @GetMapping("/getApplicationsByPositionId")
    public List<Application> getApplicationsByPositionId(@RequestParam Long positionId) {
        return positionService.getApplicationsByPositionId(positionId);
    }

    @GetMapping("/getApplicationsByEmail")
    public List<Application> getApplicationsByEmail(@RequestParam String email) {
        return positionService.getApplicationsByEmail(email);
    }

    @DeleteMapping("/delete/{positionId}")
    public ResponseEntity<String> deletePosition(@PathVariable Long positionId) {
        return positionService.delete(positionId);
    }

    @PutMapping("/application/update")
    public Application updateApplication(@RequestBody Application application) throws JobNotFoundException {
        return positionService.updateApplication(application);
    }
}
