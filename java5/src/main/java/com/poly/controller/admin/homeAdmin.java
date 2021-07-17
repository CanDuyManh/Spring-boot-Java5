package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DAO.accountDAO;
import com.poly.DAO.categoryDAO;
import com.poly.DAO.orderDAO;
import com.poly.DAO.productDAO;
import com.poly.model.account;
import com.poly.model.category;
import com.poly.model.order;
import com.poly.model.product;

@Controller
@RequestMapping("admin")
public class homeAdmin {
	@Autowired
	accountDAO accDAO ;
	@Autowired
	orderDAO orderDAO ;
	@Autowired
	categoryDAO categoryDAO ;
	@Autowired
	productDAO productDAO ;
	
	@RequestMapping("accountManager")
	public String New1(Model model) {
		List<account> list = accDAO.findAll(PageRequest.of(0,2)).getContent();
//		List<account> list = accDAO.findAll();
		System.out.println(1);
		model.addAttribute("view","/views/adminViews/accountManager.jsp");
		model.addAttribute("lstAccount",list);
		return "admin";
	}
	
	@RequestMapping("orderManager")
	public String New2(Model model) {
		List<order> list = orderDAO.findAll(PageRequest.of(0,2)).getContent();
//		List<account> list = accDAO.findAll();
		model.addAttribute("view","/views/adminViews/orderManager.jsp");
		model.addAttribute("lst",list);
		return "admin";
	}
	
	@RequestMapping("categoryManager")
	public String New3(Model model) {
		
		List<category> list = categoryDAO.findAll(PageRequest.of(0,2)).getContent();
//		List<account> list = accDAO.findAll();
		model.addAttribute("view","/views/adminViews/categoryManager.jsp");
		model.addAttribute("lst",list);
		return "admin";
	}
	@RequestMapping("productManager")
	public String New4(Model model) {
		
		List<product> list = productDAO.findAll(PageRequest.of(0,2)).getContent();
//		List<account> list = accDAO.findAll();
		model.addAttribute("view","/views/adminViews/productManager.jsp");
		model.addAttribute("lst",list);
		return "admin";
	}
	
	@RequestMapping("thongke")
	public String New5(Model model) {
		
//		List<product> list = productDAO.findAll(PageRequest.of(0,2)).getContent();
//		List<account> list = accDAO.findAll();
		model.addAttribute("view","/views/adminViews/thongKe.jsp");
//		model.addAttribute("lst",list);
		return "admin";
	}

}
