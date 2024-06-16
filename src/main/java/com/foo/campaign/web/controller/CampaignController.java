package com.foo.campaign.web.controller;


import com.foo.campaign.domain.model.Campaign;
import com.foo.campaign.service.CampaignMapper;
import com.foo.campaign.service.CampaignService;
import com.foo.campaign.web.dto.CampaignDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/campaigns")
public class CampaignController {

    private final CampaignService campaignService;
    private final CampaignMapper campaignMapper;

    @GetMapping
    public List<CampaignDto> listCampaigns() {
        List<Campaign> models = campaignService.findCampaigns();
        return models.stream()
            .map(campaignMapper::toDto)
            .toList();
    }

    @GetMapping(value = "/{id}")
    public CampaignDto findOne(@PathVariable Long id) {
        Campaign model = campaignService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return campaignMapper.toDto(model);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignDto create(@RequestBody @Valid CampaignDto newCampaign) {
        Campaign model = campaignMapper.toEntity(newCampaign);
        Campaign createdModel = this.campaignService.save(model);
        return campaignMapper.toDto(createdModel);
    }

    @PutMapping(value = "/{id}")
    public CampaignDto update(@PathVariable Long id, @RequestBody @Validated(CampaignDto.CampaignUpdateValidationData.class) CampaignDto updatedCampaign) {
        Campaign model = campaignMapper.toEntity(updatedCampaign);
        Campaign createdModel = this.campaignService.updateCampaign(id, model)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return campaignMapper.toDto(createdModel);
    }
}
