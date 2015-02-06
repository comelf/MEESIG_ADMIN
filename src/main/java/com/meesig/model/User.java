package com.meesig.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Alias("user")
public class User {
	private int 	user_id;
	@NotEmpty	@Size(min=4, max=20)
	private String 	user_login_id;
	private int 	user_crc_id;

	@NotEmpty	@Size(min=8, max=20)
	private String 	user_password;
	
	@NotEmpty
	private String 	user_name;
	
	@Email
	private String 	user_email;
	private Date 	user_b_date;
	
	@NotNull
	private String 	user_gender;
	private Date 	user_join_date;
	private Date 	user_exit_date;
	private int 	user_status; 
	private int   	user_point;	
	private int   	user_grade;	
	
	@NotNull	@NotEmpty
	private String	user_role; 	
	private String	user_location;	
	private boolean user_enabled;
	
	public User() {
		
	}
	
	public User (String user_login_id) {
		this.user_login_id = user_login_id;
	}
	
	public User(String user_login_id, String user_password,
			String user_name, String user_email, Date user_b_date,
			String user_gender, String user_location) {
		this.user_login_id = user_login_id;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_b_date = user_b_date;
		this.user_gender = user_gender;
		this.user_location = user_location;
	}
	
	public User(String user_login_id, String user_password) {
		super();
		this.user_login_id = user_login_id;
		this.user_password = user_password;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_login_id() {
		return user_login_id;
	}
	public void setUser_login_id(String user_login_id) {
		this.user_login_id = user_login_id;
	}
	public int getUser_crc_id() {
		return user_crc_id;
	}
	public void setUser_crc_id(int user_crc_id) {
		this.user_crc_id = user_crc_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Date getUser_b_date() {
		return user_b_date;
	}
	public void setUser_b_date(Date user_b_date) {
		this.user_b_date = user_b_date;
	}
	public String getUser_gender() {
		if(this.user_gender.equals("m")){
			return "남자";
		}else{
			return "여자";
		}
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public Date getUser_join_date() {
		return user_join_date;
	}
	public void setUser_join_date(Date user_join_date) {
		this.user_join_date = user_join_date;
	}
	public Date getUser_exit_date() {
		return user_exit_date;
	}
	public void setUser_exit_date(Date user_exit_date) {
		this.user_exit_date = user_exit_date;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	public int getUser_point() {
		return user_point;
	}
	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}
	public int getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(int user_grade) {
		this.user_grade = user_grade;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getUser_location() {
		return user_location;
	}
	public void setUser_location(String user_location) {
		this.user_location = user_location;
	}
	public boolean getUser_enabled() {
		return user_enabled;
	}
	public void setUser_enabled(boolean user_enabled) {
		this.user_enabled = user_enabled;
	}

	public boolean matchPassword(String user_pw) {
		if(this.user_password.equals(user_pw)){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "AdminUser [user_id=" + user_id + ", user_login_id="
				+ user_login_id + ", user_crc_id=" + user_crc_id
				+ ", user_password=" + user_password + ", user_name="
				+ user_name + ", user_email=" + user_email + ", user_b_date="
				+ user_b_date + ", user_sex=" + user_gender + ", user_role="
				+ user_role  + "user_location=" + user_location+ "]";
	}
	
	
}
