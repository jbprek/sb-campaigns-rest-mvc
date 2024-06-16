package com.foo.campaign.service;


import com.foo.campaign.domain.model.Worker;
import com.foo.campaign.web.dto.WorkerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

        WorkerDto toDto(Worker worker);

        Worker toEntity(WorkerDto worker);
}
