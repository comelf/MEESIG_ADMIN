package com.meesig.util;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.meesig.model.ShippingDays;

public class ShippingDayUtil {
	private ShippingDays shippingDays;
	private Date now;
	Calendar calendar;
	List<Date> days;
	
	public ShippingDayUtil(ShippingDays shippingDays) {
		this.shippingDays = shippingDays;
		now = new Date();
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(now);
		days = new ArrayList<Date>();
	}
	
	private Time getTime() { 
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		return Time.valueOf(String.valueOf(hour+1) +":"+ String.valueOf(minute) + ":00");
	}

	public List<Date> makeOneDayDelivery() {	
		Time nTime = getTime();
		Time sTime = shippingDays.getDay_send_time();
		
		if(nTime.before(sTime)){
			System.out.println("send time before");
			setDelivery(calendar.get(Calendar.DAY_OF_WEEK),0);
		}else{
			System.out.println("send time after");
			setDelivery(calendar.get(Calendar.DAY_OF_WEEK),1);
		}

		return days;
	}
	
	public List<Date> makeTwoDayDelivery() {	
		Time nTime = getTime();
		Time sTime = shippingDays.getDay_send_time();
		
		if(nTime.before(sTime)){
			System.out.println("send time before");
			setDelivery(calendar.get(Calendar.DAY_OF_WEEK),1);
		}else{
			System.out.println("send time after");
			setDelivery(calendar.get(Calendar.DAY_OF_WEEK),2);
		}

		return days;
	}

	
	private void setDelivery(int day, int start) {
		for(int i=0; i<Calendar.DAY_OF_WEEK; i++){
			int tDay = getDay(day, start + i);
			if(isDeliveryDay(tDay)){
				Calendar c = Calendar.getInstance();
				c.setTime(now);
				c.add(Calendar.DATE, start + i);
				Date tDate = c.getTime();
				days.add(tDate);
			}
		}
	}

	private boolean isDeliveryDay(int day){
		switch (day) {
		case Calendar.SUNDAY:
			return shippingDays.getDay_sun();
		case Calendar.MONDAY:
			return shippingDays.getDay_mon();
		case Calendar.TUESDAY:
			return shippingDays.getDay_tue();
		case Calendar.WEDNESDAY:
			return shippingDays.getDay_wed();
		case Calendar.THURSDAY:
			return shippingDays.getDay_thu();
		case Calendar.FRIDAY:
			return shippingDays.getDay_fri();
		case Calendar.SATURDAY:
			return shippingDays.getDay_sat();
			
		default:
			return false;
		}
	}
	
	private int getDay(int day, int plus) {
		return (day-1 + plus) % 7 + 1;
	}
	
	public Date getSendDay() {
		return shippingDays.getDay_send_day();
	}

	public void setNow(Date date) {
		now = date;
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(now);
	}

	public List<Date> makeOneDayDeliveryForAdmin() {
		setDelivery(calendar.get(Calendar.DAY_OF_WEEK),-1);
		return days;
	}
	
	public List<Date> makeTwoDayDeliveryForAdmin() {	
		setDelivery(calendar.get(Calendar.DAY_OF_WEEK),-1);
		return days;
	}
}
