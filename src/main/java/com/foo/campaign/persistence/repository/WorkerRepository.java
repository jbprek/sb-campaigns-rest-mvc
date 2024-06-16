package com.foo.campaign.persistence.repository;

import com.foo.campaign.domain.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
