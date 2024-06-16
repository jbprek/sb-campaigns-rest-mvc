package com.foo.campaign.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

import java.util.Set;

public record CampaignDto(

        Long id,

        @NotBlank(message = "code can't be null")
        String code,

        @NotBlank(groups = {CampaignUpdateValidationData.class, Default.class},
                message = "name can't be blank")
        String name,

        @Size(groups = {CampaignUpdateValidationData.class, Default.class},
                min = 10, max = 50,
                message = "description must be between 10 and 50 characters long")
        String description,

        Set<TaskDto> tasks) {


    public interface CampaignUpdateValidationData {
    }
}

