package com.poly.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class cartDTO {
	
	private Integer id ;
	private List<ProductDTO> products = new ArrayList<ProductDTO>();
	
}
