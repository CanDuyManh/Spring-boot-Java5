package com.poly.controller.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.DTO.ProductDTO;
import com.poly.DTO.cartDTO;
import com.poly.controller.sessionSV;
import com.poly.model.account;

@Controller
@RequestMapping("cart")

public class cartController {

	@Autowired
	sessionSV session;
	
	@RequestMapping("")
	public String New1(Model model) {
		try {
			cartDTO cart = (cartDTO) session.getAttribute("cart");
			account acc = session.getAttribute("acc");
			model.addAttribute("acc",acc);
			model.addAttribute("cart",cart);
			
		} catch (Exception e) {
			// TODO: handle exception
			cartDTO cart = new cartDTO();
			account acc = new account();
			model.addAttribute("acc",acc);
			model.addAttribute("cart",cart);
		}
	
		model.addAttribute("view","/views/homeViews/cart.jsp");
		
		return "index";
	}
	
	
	@ResponseBody
	@PostMapping("add")
	public boolean New2(ProductDTO product) {
		try {
			if(product.getQuantity() == null) {
				product.setQuantity(1);
			}
			
			cartDTO cart = (cartDTO) session.getAttribute("cart");
			for(ProductDTO p : cart.getProducts()) {
				if(p.getName().equals(product.getName())) {
					int sl =  p.getQuantity();
					sl = sl + product.getQuantity() ;
					p.setQuantity(sl);
					Double total = (p.getPrice()) * sl;
					p.setTotal(total);
					return true;
				}
			}
				
				product.setTotal(product.getPrice());
				cart.getProducts().add(product);
				session.setAttribute("cart",cart);
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			try {
				cartDTO cart = new cartDTO();
				product.setTotal(product.getPrice());
				cart.getProducts().add(product);
				session.setAttribute("cart",cart);
				return true;
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		return true;
		
	}
	
	@ResponseBody
	@RequestMapping("getSize")
	public Integer New3() {
		try {
			cartDTO cart = (cartDTO) session.getAttribute("cart");
			Integer size = 0;
			for(ProductDTO p : cart.getProducts()) {
				size = (size + p.getQuantity());
			}
			return size;
		}
			catch (Exception e) {
				return 0;
			}
	}
	
	@ResponseBody
	@RequestMapping("getProduct")
	public List<ProductDTO> New5() {
		try {
			cartDTO cart = (cartDTO) session.getAttribute("cart");
			
			return cart.getProducts();
		}
			catch (Exception e) {
				return null;
			}
	}
	
	@ResponseBody
	@DeleteMapping("remove/{id}")
	public boolean New6(@PathVariable("id") Integer id) {
		try {
			cartDTO cart = (cartDTO) session.getAttribute("cart");
			for(ProductDTO p : cart.getProducts()) {
				if(p.getId() == id) {
					cart.getProducts().remove(p);
					return true;
				}
			}
			return true;
		}
			catch (Exception e) {
				return false;
			}
	}
	
	@ResponseBody
	@PostMapping("setQuantity")
	public boolean New5(Integer id ,Integer quantity ) {
		try {
			cartDTO cart = (cartDTO) session.getAttribute("cart");
			for(ProductDTO p : cart.getProducts()) {
				if(p.getId() == id) {
					int sl = quantity;
					System.out.println(sl);
					p.setQuantity(sl);
					Double total = (p.getPrice()) * sl;
					p.setTotal(total);
					return true;
				}
			}
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			try {
				return true;
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		return true;
	}
	
	
	
	
}