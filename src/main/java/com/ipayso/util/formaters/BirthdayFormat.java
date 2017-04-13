package com.ipayso.util.formaters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ipayso.util.enums.Months;

import groovyjarjarcommonscli.ParseException;

/**
 * BirthdayFormat.class -> This class is used to convert the strings from form into a date
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Component
 */
@Component
public class BirthdayFormat {
	
	/**
	 * This method take the string parameters and convert it into a birthday date 
	 */
	public String convertRegistrationBirthday(String year, String month, String day) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), monthInInt(month), Integer.parseInt(day));
		Date date = new Date(cal.getTime().getTime());
		return dateFormat.format(date);
		
	}
	
	/**
	 * Receive an String and verify which  month it belongs
	 * @param month
	 * @return the month
	 */
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
