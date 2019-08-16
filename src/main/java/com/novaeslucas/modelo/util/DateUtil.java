package com.novaeslucas.modelo.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	
	public static List<Date> beteew(Date date){
		String sDate = dateToString(date);
		List<Date> dates = new ArrayList<Date>();
		dates.add(stringToDateTime(sDate+ " 00:00:00"));
		dates.add(stringToDateTime(sDate+ " 23:59:59"));
		return dates; 
	}
	
	public static List<Date> beteew(Date inicio, Date fim){
		String sDateInicio = dateToString(inicio);
		String sDateFim = dateToString(fim);
		List<Date> dates = new ArrayList<Date>();
		dates.add(stringToDateTime(sDateInicio+ " 00:00:00"));
		dates.add(stringToDateTime(sDateFim+ " 23:59:59"));
		return dates; 
	}
	
	
	public static String format(Date date, String pattern){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static Date stringToDate(String date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
			return sdf.parse(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static String dateToString(Date date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
			return sdf.format(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	} 
	
	public static Date stringToDateTime(String date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");		
			return sdf.parse(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static String timeToString(Date date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");		
			return date != null ? sdf.format(date) : "";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static String dateTimeToString(Date date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");		
			return sdf.format(date);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
		
	public static boolean isDayOfWeek(int day, int month, int year){
		Calendar calendar = new GregorianCalendar();
		calendar.set(year, month - 1, day);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isMonday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.MONDAY);
		
		if(dayOfWeek == Calendar.MONDAY){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isTuesday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.TUESDAY);
		
		if(dayOfWeek == Calendar.TUESDAY){
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isWednesday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.WEDNESDAY);
		
		if(dayOfWeek == Calendar.WEDNESDAY){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isThursday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.THURSDAY);
		
		if(dayOfWeek == Calendar.THURSDAY){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isFriday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.FRIDAY);
		
		if(dayOfWeek == Calendar.FRIDAY){
			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isSaturday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.SATURDAY);
		
		if(dayOfWeek == Calendar.SATURDAY){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isSunday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.set(getYear(date), getMonth(date) - 1, getDay(date));
		int dayOfWeek = calendar.get(Calendar.SUNDAY);
		
		if(dayOfWeek == Calendar.SUNDAY){
			return true;
		} else {
			return false;
		}
	}
	
	public static String getDayOfWeek(Date date){
		if(isMonday(date)){
			return "Seg";
		} else if(isTuesday(date)){
			return "Ter";
		} else if(isWednesday(date)){
			return "Qua";
		} else if(isThursday(date)){
			return "Qui";
		} else if(isFriday(date)){
			return "Sex";
		} else if(isSaturday(date)){
			return "Sab";
		} else if(isSunday(date)){
			return "Dom";
		} else {
			return "";
		}
	}
	
	
	public static int getDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String month = sdf.format(date);
		return Integer.parseInt(month);
	}
	
	public static int getDay(){
		return getDay(new Date());
	}
	
	public static int getMonth(){
		return getMonth(new Date());
	}
	
	public static int getMonth(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String month = sdf.format(date);
		return Integer.parseInt(month);
	}
	
	public static int getYear(){
		return getYear(new Date());
	}
	
	public static int getYear(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String month = sdf.format(date);
		return Integer.parseInt(month);
	}
	
}
