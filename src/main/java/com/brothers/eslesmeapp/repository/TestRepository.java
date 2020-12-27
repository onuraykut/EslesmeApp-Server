package com.brothers.eslesmeapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.brothers.eslesmeapp.model.Testler;

@Repository
public interface TestRepository extends MongoRepository<Testler,String> {
    Optional<Testler> findById(String id);
    Optional<List<Testler>> findByKategoriAndOlusturanTipi(String kategori,String olusturanTipi);
    Optional<List<Testler>> findByOlusturanUid(String uid);
    Page<Testler> findAll(Pageable pageable);
    //List<User> findBy

}