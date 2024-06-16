package com.foo.campaign.service;


import com.foo.campaign.domain.model.Campaign;
import com.foo.campaign.domain.model.Task;
import com.foo.campaign.web.dto.TaskDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { WorkerMapper.class})
public interface TaskMapper {

        @Mapping(target = "campaignId", source = "campaign.id")
        TaskDto toDto(Task task);

        Task toModel(TaskDto dto);

        @AfterMapping
        default void afterMappingToModel(TaskDto dto, @MappingTarget Task entity) {
                // Custom logic after mapping to entity
                if (dto == null || dto.campaignId() == null) {
                        return;
                }
                Campaign campaign = new Campaign();
                campaign.setId(dto.campaignId());
                entity.setCampaign(campaign);

                if (entity.getUuid() == null) {
                        entity.setUuid(java.util.UUID.randomUUID().toString());
                }

        }

}
