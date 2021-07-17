package com.poly.controller.API;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DAO.orderDAO;
import com.poly.DTO.orderDTO;
import com.poly.model.account;
import com.poly.model.order;
	@RestController
	@RequestMapping("/api/order")
public class orderAPI {
	@Autowired
	orderDAO dao;
	
	@GetMapping("")
	public Object  findAll() {
		try {
			List<orderDTO> list = new ArrayList<orderDTO>();
			for (order item : dao.findAll()) {
				orderDTO dto = new orderDTO();
				dto.toDTO(item);
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@GetMapping("/{id}")
	public Object findOne(@PathVariable("id") Integer id) {
		try {
			System.out.println(id);
			orderDTO model = new orderDTO();
			model.toDTO(dao.findById(id).get());
			
			return model;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@GetMapping("/pagination")
	public Object PhanTrang(@RequestParam("page")Integer page ,@RequestParam("limit")Integer limit) {
		try {
			System.out.println(page);
			System.out.println(limit);
			List<orderDTO> list = new ArrayList<orderDTO>();
			for (order item :  dao.findAll(PageRequest.of(page, limit)).getContent()) {
				orderDTO dto = new orderDTO();
				dto.toDTO(item);
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@PostMapping("")
	public Object addOne(order acc ) {
		try {
			dao.save(acc);
			return acc;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	@PutMapping("")
	public Object update(order acc) {
		try {
			order order = dao.findById(acc.getId()).get();
			order.setStatus(acc.getStatus());
			dao.save(order);
			return acc;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") Integer id) {
		try {
			dao.deleteById(id);
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}	
}
