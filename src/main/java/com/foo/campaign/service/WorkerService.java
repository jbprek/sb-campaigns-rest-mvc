package com.foo.campaign.service;


import com.foo.campaign.domain.model.Worker;

import java.util.Optional;

public interface WorkerService {

    Optional<Worker> findById(Long id);

    Worker save(Worker worker);

    Optional<Worker> updateWorker(Long id, Worker worker);
}
