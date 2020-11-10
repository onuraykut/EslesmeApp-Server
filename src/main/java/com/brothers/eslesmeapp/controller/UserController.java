package com.brothers.eslesmeapp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brothers.eslesmeapp.model.Bildirim;
import com.brothers.eslesmeapp.model.User;
import com.brothers.eslesmeapp.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserRepository userRepository;
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@PostMapping("/save")
	public void saveUser(@RequestParam String uid,@RequestParam String name,@RequestParam String token) {
		Optional<User> getUser = userRepository.findByUid(uid);
		if(getUser.isEmpty()) {
		User user = new User(uid,name,token);
		userRepository.save(user);
		} else {
			User user = getUser.get();
			user.setToken(token);
			userRepository.save(user);

		}
	}
	
	@PostMapping("/getNotifications")
	public List<Bildirim> getNotifications(@RequestParam String uid) {
		User user = userRepository.findByUid(uid).get();
		List<Bildirim> bildirim =  user.getBildirim();
		Collections.reverse(bildirim);
		for(int i=0;i<bildirim.size();i++) {
			User userBildirim = userRepository.findByUid(bildirim.get(i).getGonderenUid()).get();
			bildirim.get(i).setGonderenAdi(userBildirim.getName());
		}
		return bildirim;
	}
	
	
}
