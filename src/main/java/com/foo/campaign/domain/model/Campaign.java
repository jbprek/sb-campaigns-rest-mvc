package com.foo.campaign.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = false, updatable = false)
    @NotNull
    private String code;

    @NotBlank
    private String name;

    private String description;

    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    public Campaign(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign campaign)) return false;

        return Objects.equals(code, campaign.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
