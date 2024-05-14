package org.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taskmanager.models.Task;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(UUID id);

    void deleteById(UUID id);
}
