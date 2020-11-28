package com.fyp.prograd.controller;

import com.fyp.prograd.exceptions.JobNotFoundException;
import com.fyp.prograd.model.Position;
import com.fyp.prograd.service.PositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
    public List<Position> getAll() {
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
}
