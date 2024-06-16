package com.foo.campaign.web.dto;

import com.foo.campaign.domain.model.TaskStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

import java.time.LocalDate;

public record TaskDto( 
    Long id,

    String uuid,

    @NotBlank(groups = { TaskUpdateValidationData.class, Default.class },
      message = "name can't be blank")
    String name,

    @Size(groups = { TaskUpdateValidationData.class, Default.class },
      min = 10, max = 50,
      message = "description must be between 10 and 50 characters long")
    String description,

    @Future(message = "dueDate must be in the future")
    LocalDate dueDate,

    @NotNull(groups = { TaskUpdateStatusValidationData.class, TaskUpdateValidationData.class },
      message = "status can't be null")
    TaskStatus status,

    @NotNull(groups = { TaskUpdateValidationData.class, Default.class },
      message = "campaignId can't be null")
    Long campaignId,

    @Valid
    @ConvertGroup(from = Default.class, to = WorkerOnTaskCreateValidationData.class)
    WorkerDto assignee) { 


    public interface TaskUpdateValidationData {
    }

    public interface TaskUpdateStatusValidationData {
    }

    public interface TaskUpdateAssigneeValidationData {
    }

    public interface WorkerOnTaskCreateValidationData {
    }
}
