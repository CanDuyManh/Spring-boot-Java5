package com.poly.controller.API;

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

import com.poly.DAO.orderDetailDAO;
import com.poly.model.orderDetail;

	@RestController
	@RequestMapping("/api/orderDetail")
public class orderDetailAPI {

	@Autowired
	orderDetailDAO dao;
	
	@GetMapping("")
	public Object  findAll() {
		try {
			return dao.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@GetMapping("/{id}")
	public Object findOne(@PathVariable("id") Integer id) {
		try {
			System.out.println(id);
			return dao.findById(id).get();
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
			return dao.findAll(PageRequest.of(page, limit)).getContent();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@PostMapping("")
	public Object addOne(orderDetail acc ) {
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
	public Object update(orderDetail acc) {
		try {
			dao.save(acc);
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
