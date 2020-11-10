package com.brothers.eslesmeapp.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "Room")
public class Room {
	@Id
	private String id;
	private String testId;
	private String testAdi;
	private String olusturanUid;
	@JsonIgnore
	private List<Integer> olusturanCevaplari;
	@JsonIgnore
	private List<Cozenler> cozenler = new ArrayList<Cozenler>();
	private String roomLink;
	private Date createdDate = createDate();
	//private Date updatedOn;
	
	private Date createDate() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return now;
	}


	public Room(String testID, String testAdi,String olusturanUid, List<Integer> olusturanCevaplari) {
		super();
		this.testId = testID;
		this.testAdi = testAdi;
		this.olusturanUid = olusturanUid;
		this.olusturanCevaplari = olusturanCevaplari;
	}
	
	@JsonProperty("cozenSayisi")
	public int getFriendLength() {
		return cozenler.size();
	}

}
