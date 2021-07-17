package com.poly.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.DAO.accountDAO;
import com.poly.model.TestModel;
import com.poly.model.account;

@Controller
public class HomeController {
	@RequestMapping("homeTEST")
	public String home(Model model) {
		System.out.println("MOD1");
		model.addAttribute("message","Well come to Spring MVC");
		return "index";
	}
	@RequestMapping("test")
	public String New(TestModel model) {
		System.out.println(model.getId());
		System.out.println(model.getVal());
		return "index";
	}
	
	@Autowired
	accountDAO dao ;
	
	@RequestMapping("testDAO")
	public String New2(TestModel model) {
		
		account a = new account();
		a.setName("the");
		dao.save(a);
		
		return "index";
	}
	@RequestMapping("testJSP")
	public String New3(Model model) {
		
		model.addAttribute("view","/views/homeViews/home.jsp");
		
		return "index";
	}
	@RequestMapping("admin/home")
	public String New4(Model model) {
		
		model.addAttribute("view","/views/adminViews/homeAdmin.jsp");
		
		return "admin";
	}
	
	@RequestMapping("demoFile")
	public String New42(Model model) {
		
		return "lap3";
	}
	
	@Autowired
	ServletContext app;
	
	@PostMapping("uploadFile")
	public String New5(@RequestParam("photo") MultipartFile file) {
		System.out.println("Heelo");
		try {
			if(!file.isEmpty()) {
				String filename = file.getOriginalFilename();
				File f = new File(app.getRealPath("/images/"+filename));
				System.out.println(f.getPath());
				file.transferTo(f);
				
				System.out.println("da vao day");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sai");
		}


		
		return "admin";
	}
	
//	@Autowired
//	accountDAO accDAO ;
//	@RequestMapping("admin/accountManager")
//	public String New5(Model model) {
//		List<account> list = accDAO.findAll();
//		model.addAttribute("view","/views/adminViews/accountManager.jsp");
//		model.addAttribute("lstAccount",list);
//		
//		return "admin";
//	}
	
	
	
	
}
