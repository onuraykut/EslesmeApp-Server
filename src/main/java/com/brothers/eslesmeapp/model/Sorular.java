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
public class Sorular {
	private int soruTipi;
	private String soru;
	private List<String> siklar;	
}
