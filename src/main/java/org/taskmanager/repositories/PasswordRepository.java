package org.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.taskmanager.models.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
}
