package com.meesig.model;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

import org.apache.ibatis.type.Alias;

@Alias("shippingDays")
@Data
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
