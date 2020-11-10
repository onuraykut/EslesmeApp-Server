package com.brothers.eslesmeapp.model;

import java.util.Date;

import com.brothers.eslesmeapp.tools.NewDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bildirim {
	private String bildirimID;
	private String gonderenUid;
	private String gonderenAdi;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String testId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String roomId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date olusturmaTarihi = new NewDate().createDate();
	
	public Bildirim(String bildirimID, String gonderenUid) {
		super();
		this.bildirimID = bildirimID;
		this.gonderenUid = gonderenUid;
	}

	public Bildirim(String bildirimID, String gonderenUid, String testId,String roomId) {
		super();
		this.bildirimID = bildirimID;
		this.gonderenUid = gonderenUid;
		this.testId = testId;
		this.roomId = roomId;
	}
	
	
}


/*  Bildirim ID'ler
 * - arkadasEkle
 * */
