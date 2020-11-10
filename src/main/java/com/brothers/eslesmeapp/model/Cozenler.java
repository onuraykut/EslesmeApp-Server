package com.brothers.eslesmeapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cozenler {
	String cozenUid;
	String cozenName;
	List<Integer> cevaplar;
	double sonuc;
	
	public Cozenler(String cozenUid,String cozenName ,List<Integer> cevaplar) {
		super();
		this.cozenUid = cozenUid;
		this.cozenName = cozenName;
		this.cevaplar = cevaplar;
	}
	
	
}
