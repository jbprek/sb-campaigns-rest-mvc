package com.foo.campaign.persistence.repository;

import com.foo.campaign.domain.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
