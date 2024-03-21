package org.taskmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.taskmanager.models.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}
