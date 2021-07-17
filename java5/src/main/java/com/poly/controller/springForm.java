package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.staffModel;

@Controller
public class springForm {
	@RequestMapping("hello")
	public String HELLO(Model model) {
		staffModel m = new staffModel();
		m.setId("hello");
		m.setName("hello2");
		model.addAttribute("staff",m);
		return "lap3";
	}
	

}
