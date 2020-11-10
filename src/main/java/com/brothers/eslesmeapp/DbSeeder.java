package com.brothers.eslesmeapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.brothers.eslesmeapp.model.Sorular;
import com.brothers.eslesmeapp.model.Testler;
import com.brothers.eslesmeapp.model.User;
import com.brothers.eslesmeapp.repository.TestRepository;
import com.brothers.eslesmeapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DbSeeder implements CommandLineRunner {
    private TestRepository testRepository;
    private UserRepository userRepository;

    public DbSeeder(TestRepository testRepository,UserRepository userRepository) {
        this.testRepository = testRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... strings) throws Exception {	
       
    	 /*  	List<Sorular> sorular = new ArrayList<Sorular>();
    	List<String> siklar = new ArrayList<String>();
    	siklar.add("asd");
    	siklar.add("asd22");
    	siklar.add("asd32");
    	siklar.add("asd42");
    	for(int i=0;i<10;i++)
    	sorular.add(new Sorular(1,"Bu bir test sorusudur "+i,siklar));
    	sorular.add(new Sorular(0,"Bu bir test sorusudur X ",siklar));
        Testler testler1 = new Testler(
        		"Aşk kelimesini duyduğunda önce hangisi aklına geliyor?",
        		"Aşk",
        		"test.jpg",
        		"123",
        		"Ekip",
        		sorular
        		);
        Testler testler2 = new Testler(
        		"Bu bir testtir 2",
        		"Aşk",
        		"test.jpg",
        		"123",
        		"Ekip",
        		sorular
        		);
    	
       this.testRepository.deleteAll();
          List<Testler> testler = new ArrayList<>();
        for(int i=0;i<20;i++)
        testler.add(testler1); 

        this.testRepository.saveAll(testler); 
        
        this.userRepository.deleteAll();
        User user = new User("Pj7l9ZOKOOVJzyqqnGV1DGabtcx1","Avel");
        User user2 = new User("EjNfVY1dLyVtohEPrGUUxdnVOCk1","Avel2");

        this.userRepository.save(user);
        this.userRepository.save(user2); */
      
     /*   List<TestPaylasimi> testPaylasimii = new ArrayList<>();
        for(int i=0;i<100;i++) 
        testPaylasimii.add(test1); 
        

        this.testPaylasimiRepository.deleteAll();
        this.testPaylasimiRepository.saveAll(testPaylasimii); */
        
    } 
   
} 
