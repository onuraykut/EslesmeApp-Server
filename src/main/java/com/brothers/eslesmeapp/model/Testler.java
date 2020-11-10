package com.brothers.eslesmeapp.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.brothers.eslesmeapp.tools.NewDate;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Document(collection = "Testler")
@JsonFilter("filterByName")
public class Testler {
	@Id
	private String id;
	private String testAdi;
	private String kategori;
	private String image;
	private String olusturanUid;
	private String olusturanTipi;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date olusturmaTarihi = new NewDate().createDate();
	private List<Sorular> sorular;
	
	public Testler(String testAdi, String kategori, String image, String olusturanUid, String olusturanTipi,
		List<Sorular> sorular) {
		super();
		this.testAdi = testAdi;
		this.kategori = kategori;
		this.image = image;
		this.olusturanUid = olusturanUid;
		this.olusturanTipi = olusturanTipi;
		this.sorular = sorular;
	}
}
