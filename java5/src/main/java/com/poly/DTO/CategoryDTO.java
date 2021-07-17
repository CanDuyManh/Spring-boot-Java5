package com.poly.DTO;

import org.springframework.stereotype.Component;

import com.poly.model.category;

import lombok.Data;

@Data
@Component
public class CategoryDTO {
	private Integer id;	
	private String name;
	
	public void toDTO(category category) {
		if(category == null) {
			return;
		}
		this.id = category.getId();
		this.name = category.getName();
	}
	
}
