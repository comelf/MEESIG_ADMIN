package com.meesig.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertUtil {
	private static NumberFormat numberFormatter = NumberFormat.getInstance();
	private static SimpleDateFormat forMyPage = new SimpleDateFormat("YYYY.MM.dd");
	private static SimpleDateFormat forTimeStamp = new SimpleDateFormat("YYMMddHHmmss");
	private static SimpleDateFormat forResultPage = new SimpleDateFormat("YYYY년 MM월 dd일 HH시 mm분 ss초");
	private static SimpleDateFormat forMenu = new SimpleDateFormat("MM월 dd일 (E)");
	private static SimpleDateFormat date = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
	
	public static String currency(long order_total_price){
		return numberFormatter.format(order_total_price) + "원";
	}

	public static String dateForMyPage(Date order_date) {
		return forMyPage.format(order_date);
	}

	public static String dateForTimeStamp(Date now) {
		return forTimeStamp.format(now);
	}

	public static String dateForResultPage(Date date) {
		return forResultPage.format(date);
	}
	
	public static String dateForMenu(String sDate) {
		try {
			Date dDay = date.parse(sDate);
			return forMenu.format(dDay);
		} catch (ParseException e){
			return "";
		}
	}

	public static Date stringToDate(String deliveryDay) {
		try {
			return date.parse(deliveryDay);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateForMenu(Date date) {
		return forMenu.format(date);
	}
}
