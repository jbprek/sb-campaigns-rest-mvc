package com.foo.campaign.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID()
        .toString();

    @NotBlank
    private String name;

    private String description;

    private LocalDate dueDate;

    @NotNull
    private TaskStatus status;

    @ManyToOne(optional = false)
    @NotNull
    private Campaign campaign;

    @ManyToOne
    private Worker assignee;



    public Task(String name, String description, LocalDate dueDate, Campaign campaign, TaskStatus status, Worker assignee, String uuid) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.campaign = campaign;
        this.assignee = assignee;
        if (!Objects.isNull(uuid)) {
            this.uuid = uuid;
        }
    }



    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

}
