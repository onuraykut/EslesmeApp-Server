package com.brothers.eslesmeapp.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomKaydet {
	  private String testId;
	  private String testAdi;
	  private String paylasanUid;
	  private List<Integer> paylasanCevaplari;
}
