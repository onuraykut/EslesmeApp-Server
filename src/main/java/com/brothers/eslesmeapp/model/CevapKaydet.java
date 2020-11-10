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
public class CevapKaydet {
	private String uid;
	private String name;
	private List<Integer> cevaplar;
	private String roomId;
}
