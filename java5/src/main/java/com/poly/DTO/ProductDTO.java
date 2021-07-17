package com.poly.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.poly.model.category;
import com.poly.model.orderDetail;
import com.poly.model.product;

import lombok.Data;

@Data
@Component
public class ProductDTO {

	private Integer id;
	
	private String name;
	
	private String photo;
	
	private double price;
	
	private boolean available;

	private Date createDate = new Date();
	
	private List<orderDetail> orderDetails = null;
	
	private CategoryDTO category = new CategoryDTO();
	
	private Integer quantity; 
	
	private Double total;

	public void toDTO(product product) {
		if(product == null) {
			return;
		}
		this.orderDetails = product.getOrderDetail();
		this.id = product.getId();
		this.name = product.getName();
		this.photo = product.getPhoto();
		this.price = product.getPrice();
		this.available = product.isAvailable();
		this.createDate = product.getCreateDate();
		this.category.toDTO(product.getCategory());
	}
	
}
