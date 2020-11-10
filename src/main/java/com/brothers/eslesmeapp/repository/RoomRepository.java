package com.brothers.eslesmeapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.brothers.eslesmeapp.model.Room;
import com.brothers.eslesmeapp.model.Testler;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {
    Optional<Room> findById(String id);
    Page<Room> findByIdIn(List<String> id,Pageable page);
    
    //List<User> findBy

}