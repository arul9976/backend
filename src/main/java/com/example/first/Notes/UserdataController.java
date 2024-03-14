package com.example.first.Notes;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin
public class UserdataController {

    @Autowired
    UserDataService userDataService;

    List<UserData> UserData = new ArrayList<>();

    @PostMapping("/userDataAdd")
    public UserData SetUserData(@RequestBody UserData newuserData) {
        return userDataService.setUserData(newuserData);
    }

    @PostMapping("/createCollection/{name}")
    public void CreateCollection(@PathVariable String name) {
        userDataService.CreateCollection(name);
    }

    @GetMapping("/userDataList/{collectionName}")
    public List<UserData> GetUserData(@PathVariable String collectionName) {
        return userDataService.getUserData(collectionName);
    }

    @GetMapping("/userData/{collectionName}/{id}")
    public UserData FindOneById(@PathVariable String collectionName, @PathVariable String id) {
        return userDataService.FindOneById(collectionName, id);
    }

    @DeleteMapping("/userData/RemoveData/{CollectionName}/{id}")
    public ResponseEntity<?> DeleteDataById(@PathVariable String CollectionName, @PathVariable String id){
        try{
            return new ResponseEntity<>(userDataService.DeleteUserById(id, CollectionName), HttpStatus.OK);
        }
        catch(Exception E){
           return new ResponseEntity<>(E.toString(), HttpStatus.OK);

        }
        
    }
}
