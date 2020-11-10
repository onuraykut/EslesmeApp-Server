package com.brothers.eslesmeapp.tools;

public enum BildirimKodlari {
	arkadasEkle("arkadasEkle"),
	arkadasKabulEtti("arkadasKabulEtti"),
	arkadasSil("arkadasSil"),
	arkadasIstekCek("arkadasIstekCek"),
	arkadasIstekKabul("arkadasIstekKabul"),
	arkadasIstekRed("arkadasIstekRed"),
	kisiylePaylas("kisiylePaylas"),
	testCozuldu("testCozuldu"),
	;

	  private String name;

	  private BildirimKodlari(String name) {
	    this.name = name;
	  }

	  public String getName() {
	    return name;
	  }

	  public static BildirimKodlari getByName(String name){
	    return   BildirimKodlari.valueOf(name);
	  }
}
