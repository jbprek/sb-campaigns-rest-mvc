package com.foo.campaign.web.controller;

import com.foo.campaign.domain.model.Worker;
import com.foo.campaign.service.WorkerService;
import com.foo.campaign.web.dto.WorkerDto;
import com.foo.campaign.web.dto.WorkerDto.WorkerUpdateValidationData;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    private WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(value = "/{id}")
    public WorkerDto findOne(@PathVariable Long id) {
        Worker model = workerService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return WorkerDto.Mapper.toDto(model);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkerDto create(@RequestBody @Valid WorkerDto newWorker) {
        Worker model = WorkerDto.Mapper.toModel(newWorker);
        Worker createdModel = this.workerService.save(model);
        return WorkerDto.Mapper.toDto(createdModel);
    }

    @PutMapping(value = "/{id}")
    public WorkerDto update(@PathVariable Long id, @RequestBody @Validated(WorkerUpdateValidationData.class) WorkerDto updatedWorker) {
        Worker model = WorkerDto.Mapper.toModel(updatedWorker);
        Worker createdModel = this.workerService.updateWorker(id, model)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return WorkerDto.Mapper.toDto(createdModel);
    }
}
