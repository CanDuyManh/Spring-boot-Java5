package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DAO.accountDAO;
import com.poly.model.account;

@Controller
@RequestMapping("login")
public class loginController {
	
	@Autowired
	sessionSV session;

	
	@Autowired
	accountDAO dao;
	
	@RequestMapping("")
	public String New1(Model model) {
		try {
			account a = (account)session.getAttribute("acc");
			System.out.println(a.getName());
			return "redirect:home";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "login";
	}
	
	@PostMapping("")
	public String New2(Model model , account acc) {
		try {
			account acc2 = dao.findByUserName(acc.getUserName()).get(0);
			String pass =  acc2.getPassword();
			if(acc.getPassword().equalsIgnoreCase(pass)) {
				session.setAttribute("acc", acc2);
				return "redirect:home";
			}else {
				model.addAttribute("error","Sai tai khoan hoac mat khau !");
			}
			model.addAttribute("error","loi dang nhap hay dang nhap lai !");
			return "login";
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error","loi dang nhap hay dang nhap lai !");
			return "login";
		}
		
	}
	@DeleteMapping("")
	public void New4(Model model) {
		try {
			session.removeAttribute("acc");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@RequestMapping("/create")
	public String create(Model model) {
		
		return "dangKy";
	}
	
	@PostMapping("/create")
	public String create2(Model model,account acc,String Repassword) {
		
		if(acc.getPassword().equalsIgnoreCase(Repassword)) {
			dao.save(acc);
			model.addAttribute("error","Đăng ký thành công, hãy đăng nhập !");
			return "login";
		}else {
			model.addAttribute("error","Hãy nhập lại đúng mật khẩu !");
			return "dangKy";
		}
		
		
	}
	
}
