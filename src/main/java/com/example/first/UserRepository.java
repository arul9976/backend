package com.example.first;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * UserRepository
 */

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'username': ?0}")
    List<User> findByUsername(String Username);

}
