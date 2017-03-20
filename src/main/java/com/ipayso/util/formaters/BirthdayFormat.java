package com.ipayso.util.formaters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ipayso.util.enums.Months;

import groovyjarjarcommonscli.ParseException;

@Component
public class BirthdayFormat {
	
	public String convertRegistrationBirthday(String year, String month, String day) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), monthInInt(month), Integer.parseInt(day));
		Date date = new Date(cal.getTime().getTime());
		return dateFormat.format(date);
		
	}
	
	private int monthInInt(String month){
		if(month == Months.JANUARY.getDescription()){
			return Calendar.JANUARY;
		}
		if(month == Months.FEBRUARY.getDescription()){
			return Calendar.FEBRUARY;
		}
		if(month == Months.MARCH.getDescription()){
			return Calendar.MARCH;
		}
		if(month == Months.APRIL.getDescription()){
			return Calendar.APRIL;
		}
		if(month == Months.MAY.getDescription()){
			return Calendar.MAY;
		}
		if(month == Months.JUNE.getDescription()){
			return Calendar.JUNE;
		}
		if(month == Months.JULY.getDescription()){
			return Calendar.JULY;
		}
		if(month == Months.AUGUST.getDescription()){
			return Calendar.AUGUST;
		}
		if(month == Months.SEPTEMBER.getDescription()){
			return Calendar.SEPTEMBER;
		}
		if(month == Months.OCTOBER.getDescription()){
			return Calendar.OCTOBER;
		}
		if(month == Months.NOVEMBER.getDescription()){
			return Calendar.NOVEMBER;
		}
		if(month == Months.DECEMBER.getDescription()){
			return Calendar.DECEMBER;
		}
		return 0;
	}

}
