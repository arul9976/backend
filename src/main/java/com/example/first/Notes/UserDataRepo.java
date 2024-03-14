package com.example.first.Notes;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDataRepo extends MongoRepository<UserData, String> {

}
