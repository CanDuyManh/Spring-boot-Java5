package com.poly.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DAO.categoryDAO;
import com.poly.DAO.orderDAO;
import com.poly.DAO.productDAO;
import com.poly.controller.sessionSV;
import com.poly.model.account;
import com.poly.model.order;
import com.poly.model.product;

@Controller
@RequestMapping("home")
public class IndextController {
	
	@Autowired
	sessionSV session;
	
	@Autowired
	productDAO productDAO;
	
	@Autowired
	orderDAO orderDAO;
	@Autowired
	categoryDAO categoryDAO;
	
	@RequestMapping("")
	public String New1(Model model) {
		model.addAttribute("view","/views/homeViews/index.jsp");
		return "index";
	}
	@RequestMapping("about")
	public String New2(Model model) {
		model.addAttribute("view","/views/homeViews/about.jsp");
		return "index";
	}
	@RequestMapping("shop")
	public String New3(Model model) {
		model.addAttribute("lstCategory",categoryDAO.findAll());
		model.addAttribute("view","/views/homeViews/shop.jsp");
		return "index";
	}
	@RequestMapping("contact")
	public String New4(Model model) {
		model.addAttribute("view","/views/homeViews/contact.jsp");
		return "index";
	}
	@RequestMapping("product/view/{id}")
	public String New5(Model model , @PathVariable("id") Integer id) {
		
		try {
			product product = productDAO.findById(id).get();
			List<product> lst = productDAO.findAll();
			lst.remove(product);
			model.addAttribute("lst",lst);	
			model.addAttribute("product",product);	
			model.addAttribute("view","/views/homeViews/shop-single.jsp");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("view","/views/homeViews/shop-single.jsp");
			return "index";
		}
		
		
		
	}
	@RequestMapping("/account")
	public String New5(Model model) {
		
		account acc = session.getAttribute("acc");
		
		model.addAttribute("acc", acc);
		
		model.addAttribute("view","/views/homeViews/account.jsp");
		return "index";
	}
	
	@RequestMapping("/order")
	public String New6(Model model) {
		
		account acc = session.getAttribute("acc");
		List<order> lst = orderDAO.findByAccount(acc);
		model.addAttribute("lst", lst);
		model.addAttribute("acc", acc);
//		System.out.println(acc.getEmail());
//		
		model.addAttribute("view","/views/homeViews/order.jsp");
		return "index";
	}

	
	
	
}
