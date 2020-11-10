package com.brothers.eslesmeapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.brothers.eslesmeapp.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findById(String id);
    Optional<User> findByUid(String uid);
    Optional<List<User>> findByNameStartingWithIgnoreCase(String name);
    List<User> findByUidIn(List<String> uid);
    List<User> findByUidInAndNameStartingWithIgnoreCase(List<String> uid,String name);

    //List<User> findBy

}