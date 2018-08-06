package com.project.packman.packman.repository;

import com.project.packman.packman.model.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Roles,String> {
    Optional<Roles> findByRole(String role);
}
