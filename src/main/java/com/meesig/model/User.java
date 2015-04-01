package com.meesig.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Alias("user")
@Data
@ToString
public class User {
	private int 	user_id;
	@NotEmpty	@Size(min=4, max=20) @Pattern(regexp = "^[A-Za-z]{1}[A-Za-z0-9]{3,20}$")
	private String 	user_login_id;
	private int 	user_crc_id;

	@NotEmpty	@Size(min=6, max=20) @Pattern(regexp = "^[A-Za-z0-9!@#$%^&+=*()]{6,20}$")
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

	public boolean matchPassword(String user_pw) {
		if(this.user_password.equals(user_pw)){
			return true;
		}
		return false;
	}
}
