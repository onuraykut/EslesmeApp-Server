package com.brothers.eslesmeapp.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersRoom {
	List<String> myRooms = new ArrayList<String>();
	List<String> myHiddenRooms = new ArrayList<String>();
	List<String> mySharedWithMeRooms = new ArrayList<String>();
	List<String> mySharedHiddenRooms = new ArrayList<String>();
}
