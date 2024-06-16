package com.foo.campaign.web.dto;

import com.foo.campaign.web.dto.TaskDto.TaskUpdateAssigneeValidationData;
import com.foo.campaign.web.dto.TaskDto.TaskUpdateValidationData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WorkerDto(

    @NotNull(groups = { TaskUpdateValidationData.class, TaskUpdateAssigneeValidationData.class },
      message = "Worker id can't be null")
    Long id,

    @NotBlank(message = "email can't be blank")
    @Email(message = "You must provide a valid email address")
    String email,

    String firstName,

    String lastName) { 

    public interface WorkerUpdateValidationData {
    }
}
