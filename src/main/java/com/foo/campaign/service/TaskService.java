package com.foo.campaign.service;



import com.foo.campaign.domain.model.Task;
import com.foo.campaign.domain.model.TaskStatus;
import com.foo.campaign.domain.model.Worker;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> searchTasks(String nameSubstring, Long assigneeId);

    Optional<Task> findById(Long id);

    Task save(Task task);

    Optional<Task> updateTask(Long id, Task task);

    Optional<Task> updateStatus(Long id, TaskStatus status);

    Optional<Task> updateAssignee(Long id, Worker assignee);
}
