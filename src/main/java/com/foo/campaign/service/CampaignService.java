package com.foo.campaign.service;


import com.foo.campaign.domain.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    List<Campaign> findCampaigns();

    Optional<Campaign> findById(Long id);

    Campaign save(Campaign campaign);

    Optional<Campaign> updateCampaign(Long id, Campaign campaign);
}
