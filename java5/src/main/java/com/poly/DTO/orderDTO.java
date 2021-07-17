package com.poly.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.poly.model.order;
import com.poly.model.orderDetail;

import lombok.Data;

@Data
@Component
public class orderDTO {
	private Integer id;

	private AccountDTO account = new AccountDTO();
	
	private Date createDate = new Date();
	
	private String address;
	
	private String status;
	
	List<orderDetail> orderDetails;
	
	private List<ProductDTO> product = new ArrayList<>();
	
	public void toDTO(order order) {
		if(order == null) {
			return;
		}
		for( orderDetail item : order.getOrderDetails()){
			ProductDTO dto = new ProductDTO();
			dto.toDTO(item.getProduct());
			product.add(dto) ;
		}
		this.id = order.getId();
		this.account.ToDTO(order.getAccount());
		this.createDate = order.getCreateDate();
		this.address = order.getAddress();
		this.status = order.getStatus();
		this.orderDetails = order.getOrderDetails();
	}
	

}
