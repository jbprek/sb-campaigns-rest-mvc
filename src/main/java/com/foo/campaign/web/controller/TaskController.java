package com.foo.campaign.web.controller;

import com.foo.campaign.domain.model.Task;
import com.foo.campaign.service.TaskService;
import com.foo.campaign.web.dto.TaskDto;
import com.foo.campaign.web.dto.TaskDto.TaskUpdateAssigneeValidationData;
import com.foo.campaign.web.dto.TaskDto.TaskUpdateStatusValidationData;
import com.foo.campaign.web.dto.TaskDto.TaskUpdateValidationData;
import com.foo.campaign.web.dto.WorkerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> searchTasks(@RequestParam(required = false) String name, @RequestParam(required = false) Long assigneeId) {
        List<Task> models = taskService.searchTasks(name, assigneeId);
        List<TaskDto> taskDtos = models.stream()
            .map(TaskDto.Mapper::toDto)
            .collect(Collectors.toList());
        return taskDtos;
    }

    @GetMapping(value = "/{id}")
    public TaskDto findOne(@PathVariable Long id) {
        Task model = taskService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TaskDto.Mapper.toDto(model);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody @Valid TaskDto newTask) {
        Task model = TaskDto.Mapper.toModel(newTask);
        Task createdModel = this.taskService.save(model);
        return TaskDto.Mapper.toDto(createdModel);
    }

    @PutMapping(value = "/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody @Validated(TaskUpdateValidationData.class) TaskDto updatedTask) {
        Task model = TaskDto.Mapper.toModel(updatedTask);
        Task createdModel = this.taskService.updateTask(id, model)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TaskDto.Mapper.toDto(createdModel);
    }

    @PutMapping(value = "/{id}/status")
    public TaskDto updateStatus(@PathVariable Long id, @RequestBody @Validated(TaskUpdateStatusValidationData.class) TaskDto taskWithStatus) {
        Task updatedModel = this.taskService.updateStatus(id, taskWithStatus.status())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TaskDto.Mapper.toDto(updatedModel);
    }

    @PutMapping(value = "/{id}/assignee")
    public TaskDto updateAssignee(@PathVariable Long id, @RequestBody @Validated(TaskUpdateAssigneeValidationData.class) TaskDto taskWithAssignee) {
        Task updatedModel = this.taskService.updateAssignee(id, WorkerDto.Mapper.toModel(taskWithAssignee.assignee()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TaskDto.Mapper.toDto(updatedModel);
    }
}
