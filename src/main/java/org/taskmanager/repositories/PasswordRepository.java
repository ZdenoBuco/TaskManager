package org.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taskmanager.models.Password;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
}
