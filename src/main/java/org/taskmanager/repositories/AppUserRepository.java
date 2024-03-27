package org.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taskmanager.models.AppUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
     boolean existsAppUserByEmail(String email);
     Optional<AppUser> findAppUserByEmail(String email);
     Optional<AppUser> findAppUserById(UUID uuid);

}
