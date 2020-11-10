package com.brothers.eslesmeapp.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "User")
public class User {
	@Id
	private String id;
	@Indexed(unique = true)
	private String uid;
	private String name;
	private String token;
	private UsersRoom usersRoom = new UsersRoom();
	private List<Bildirim> bildirim = new ArrayList<>();
	private List<String> favoriler = new ArrayList<String>();

	
	public User(String uid, String name) {
		super();
		this.uid = uid;
		this.name = name;
	}


	public User(String uid, String name, String token) {
		super();
		this.uid = uid;
		this.name = name;
		this.token = token;
	}
	
	
	
}
