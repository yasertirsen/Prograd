package com.fyp.prograd.controller;

import com.fyp.prograd.exceptions.JobNotFoundException;
import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.Position;
import com.fyp.prograd.service.PositionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    private final String AUTH_TOKEN = "x-api-key";

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Position add(@Valid @RequestBody Position position){
        return positionService.add(position);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Position> getAll(@RequestHeader(AUTH_TOKEN) String bearerToken) {
        return positionService.getAll();
    }

    @GetMapping(value = "/findById", produces = "application/json")
    public Position findById(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam Long id) throws JobNotFoundException {
        return positionService.findById(id);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Position update(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody Position position) {
        return positionService.update(position);
    }

    @GetMapping(value= "/getCompanyPositions/{companyId}", produces = "application/json")
    Set<Position> getCompanyPositions(@RequestHeader(AUTH_TOKEN) String bearerToken, @PathVariable Long companyId) {
        return positionService.getCompanyPositions(companyId);
    }
}
