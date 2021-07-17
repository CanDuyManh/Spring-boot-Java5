package com.poly.DTO;

import org.springframework.stereotype.Component;

import com.poly.model.account;

import lombok.Data;

@Data
@Component
public class AccountDTO {
	
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private String name;
	
	private String email;

	private String photo;
	
	private boolean activated;
	
	private boolean admin;
	
	public void ToDTO(account acc) {
		if(acc == null) {
			return;
		}
		this.id = acc.getId();
		this.userName = acc.getUserName();
		this.password = acc.getPassword();
		this.name = acc.getName();
		this.email = acc.getEmail();
		this.photo = acc.getPhoto();
		this.activated = acc.isActivated();
		this.admin = acc.isAdmin();
	}

	
}
