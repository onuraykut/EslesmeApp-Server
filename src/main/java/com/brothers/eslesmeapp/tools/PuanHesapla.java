package com.brothers.eslesmeapp.tools;

import java.util.List;
import com.brothers.eslesmeapp.model.Sorular;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class PuanHesapla {
	private List<Integer> olusturanCevaplari;
	private List<Integer> cozenCevaplari;
	private List<Sorular> sorular;
	
	public double hesapla() {
		double toplam = 0;
		for(int i=0;i<sorular.size();i++) {
			int soruTipi = sorular.get(i).getSoruTipi();
			int olusturanCevap = olusturanCevaplari.get(i);
			int cozenCevap = cozenCevaplari.get(i);
			switch (soruTipi) {
			case 2:
				double puan = 100-Math.abs((olusturanCevap-cozenCevap));
				toplam+=puan;
				break;
			default:
				if(olusturanCevap == cozenCevap)
					toplam+=100;
				break;
			}
		}
		toplam/=sorular.size();
		toplam=round(toplam,2);
		return toplam;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
