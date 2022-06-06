package dev.sirosh.raskatbackend.repository;

import dev.sirosh.raskatbackend.entity.Environment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnvironmentRepository extends MongoRepository<Environment, String> {
}
