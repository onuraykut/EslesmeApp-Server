package com.brothers.eslesmeapp.tools;

import java.util.Calendar;
import java.util.Date;

public class NewDate {
	public Date createDate() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return now;
	}
}
