package com.foo.campaign.service.impl;

import com.foo.campaign.domain.model.Worker;
import com.foo.campaign.persistence.repository.WorkerRepository;
import com.foo.campaign.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class DefaultWorkerService implements WorkerService {

    private final WorkerRepository workerRepository;

    @Override
    public Optional<Worker> findById(Long id) {
        return workerRepository.findById(id);
    }

    @Override
    public Worker save(Worker worker) {
        worker.setId(null);
        return workerRepository.save(worker);
    }

    @Override
    public Optional<Worker> updateWorker(Long id, Worker worker) {
        return workerRepository.findById(id)
            .map(base -> updateFields(base, worker))
            .map(workerRepository::save);
    }

    private Worker updateFields(Worker base, Worker updatedWorker) {
        base.setFirstName(updatedWorker.getFirstName());
        base.setLastName(updatedWorker.getLastName());
        return base;
    }
}
