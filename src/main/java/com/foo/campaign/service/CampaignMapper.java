package com.foo.campaign.service;

import com.foo.campaign.domain.model.Campaign;
import com.foo.campaign.web.dto.CampaignDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface CampaignMapper {

    CampaignDto toDto(Campaign campaign);

    Campaign toEntity(CampaignDto campaign);

}
