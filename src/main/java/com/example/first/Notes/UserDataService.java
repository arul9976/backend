package com.example.first.Notes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class UserDataService {
    @Autowired
    UserDataRepo userDataRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    public void CreateCollection(String Name) {
        mongoTemplate.createCollection(Name);
    }

    public UserData FindOneById(String collectionName, String id) {
        return mongoTemplate.findById(id, UserData.class, collectionName);
    }

    public UserData setUserData(UserData userData) {
        return mongoTemplate.save(userData, userData.getUsername());
    }

    public List<UserData> getUserData(String Username) {
        return mongoTemplate.findAll(UserData.class, Username);
    }

    public String UpdateData(String id, String CollectionName, UserData updatedData) {
        try {
            Update update = new Update().set("Heading", updatedData.getHeading()).set("Content",
                    updatedData.getContent()).set("Radio", updatedData.getRadio());
            mongoTemplate.findAndModify(query(where("_id").is(id)), update,
                    FindAndModifyOptions.options().returnNew(true), UserData.class, CollectionName);
            return "Data Updated";
        }

        catch (Exception E) {
            throw E;
        }

    }

    public DeleteResult DeleteUserById(String id, String CollectionName) {
        try {
            return mongoTemplate.remove(query(where("_id").is(id)), CollectionName);
        } catch (Exception E) {
            throw E;
        }

    }

}
