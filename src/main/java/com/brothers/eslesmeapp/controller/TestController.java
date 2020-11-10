package com.brothers.eslesmeapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brothers.eslesmeapp.model.Testler;
import com.brothers.eslesmeapp.model.User;
import com.brothers.eslesmeapp.repository.TestRepository;
import com.brothers.eslesmeapp.repository.UserRepository;
import com.brothers.eslesmeapp.tools.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/test")
public class TestController {
private TestRepository testRepository;
private UserRepository userRepository;
	
	public TestController(TestRepository testRepository,UserRepository userRepository) {
		this.testRepository = testRepository;
		this.userRepository = userRepository;
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllTests(@RequestParam(defaultValue = "0") int page)  {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(
	    	    Order.desc("id")));
		Page<Testler> testler = testRepository.findAll(pageable);
		return new JsonFilter().getFilteredString(testler.getContent(), "filterByName", "sorular");	
	}
	
	@PostMapping("/id")
	public Optional<Testler> getTestById(@RequestParam String id) {
		return testRepository.findById(id);
	}
	@PostMapping(path = "/kategori",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<List<Testler>> getTestByKategori(@RequestParam String kategori) {
		return testRepository.findByKategori(kategori);
	}
	
	@PostMapping("/save")
	public void saveTest(@RequestBody Testler test) {
		System.out.println(test.toString());
		testRepository.save(test);
		
	}
}
