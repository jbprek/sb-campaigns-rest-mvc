package com.foo.campaign.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatus {
    //@formatter:off
    TO_DO("To Do"), 
    IN_PROGRESS("In Progress"), 
    ON_HOLD("On Hold"), 
    DONE("Done");
    //@formatter:on

    private final String label;

    @JsonValue
    public String toValue() {
        return this.getLabel();
    }
}