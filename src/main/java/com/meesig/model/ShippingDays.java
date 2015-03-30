package com.meesig.model;

import java.sql.Time;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("shippingDays")
public class ShippingDays {
	private int day_id;
	private int item_id;
	private Boolean day_mon = false;
	private Boolean day_tue = false;
	private Boolean day_wed = false;
	private Boolean day_thu = false;
	private Boolean day_fri = false;
	private Boolean day_sat = false;
	private Boolean day_sun = false;
	private Time day_send_time;
	private Date day_send_day;

	public ShippingDays() {

	}

	public int getDay_id() {
		return day_id;
	}

	public void setDay_id(int day_id) {
		this.day_id = day_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public Boolean getDay_mon() {
		return day_mon;
	}

	public void setDay_mon(Boolean day_mon) {
		this.day_mon = day_mon;
	}

	public Boolean getDay_tue() {
		return day_tue;
	}

	public void setDay_tue(Boolean day_tue) {
		this.day_tue = day_tue;
	}

	public Boolean getDay_wed() {
		return day_wed;
	}

	public void setDay_wed(Boolean day_wed) {
		this.day_wed = day_wed;
	}

	public Boolean getDay_thu() {
		return day_thu;
	}

	public void setDay_thu(Boolean day_thu) {
		this.day_thu = day_thu;
	}

	public Boolean getDay_fri() {
		return day_fri;
	}

	public void setDay_fri(Boolean day_fri) {
		this.day_fri = day_fri;
	}

	public Boolean getDay_sat() {
		return day_sat;
	}

	public void setDay_sat(Boolean day_sat) {
		this.day_sat = day_sat;
	}

	public Boolean getDay_sun() {
		return day_sun;
	}

	public void setDay_sun(Boolean day_sun) {
		this.day_sun = day_sun;
	}

	public Time getDay_send_time() {
		return day_send_time;
	}

	public void setDay_send_time(Time day_send_time) {
		this.day_send_time = day_send_time;
	}

	public Date getDay_send_day() {
		return day_send_day;
	}

	public void setDay_send_day(Date day_send_day) {
		this.day_send_day = day_send_day;
	}

	public void setDays(String[] deliveryDayOpt1) {
		setDaysBoolean(false);
		for (String opt : deliveryDayOpt1) {
			setDayBooleanAtIndex(Integer.valueOf(opt));
		}
	}

	private void setDayBooleanAtIndex(int value) {
		switch (value) {
		case 1:
			this.day_mon = true;
			break;
		case 2:
			this.day_tue = true;
			break;
		case 3:
			this.day_wed = true;
			break;
		case 4:
			this.day_thu = true;
			break;
		case 5:
			this.day_fri = true;
			break;
		case 6:
			this.day_sat = true;
			break;
		case 7:
			this.day_sun = true;
			break;

		default:
			break;
		}
	}

	private void setDaysBoolean(boolean b) {
		this.day_mon = false;
		this.day_tue = false;
		this.day_wed = false;
		this.day_thu = false;
		this.day_fri = false;
		this.day_sat = false;
		this.day_sun = false;
	}

	public void setDay_send_time_string(String time) {
		this.day_send_time = Time.valueOf(time + ":00");
	}

}
