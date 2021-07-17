package com.poly.controller.API;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DAO.accountDAO;
import com.poly.controller.sessionSV;
import com.poly.model.account;
import com.poly.model.order;

@RestController
@RequestMapping("/api/account")
public class accountAPI {

	@Autowired
	sessionSV session;
	
	@Autowired
	accountDAO dao;
	
	@Autowired
	JavaMailSender sender;
	
	@GetMapping("")
	public Object findAll() {
		try {
			List<account> acc = dao.findAll();
//			for (account account : acc) {
//				for (order order : account.getOrders()) {
//					order.setAccount(null);
//					order.setOrderDetails(null);
//				}
//			}
			return acc;
//			return dao.findAll();
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
	public Object addOne(account acc ) {
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
	public Object update(account acc) {
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
	
	@PostMapping("/change")
	public Object newUPdate(account newAcc) {
		try {
			account acc = session.getAttribute("acc");
			account oldAcc = dao.findById(acc.getId()).get();
			oldAcc.setEmail(newAcc.getEmail());
			oldAcc.setName(newAcc.getName());
			oldAcc.setUserName(newAcc.getUserName());
			session.setAttribute("acc", oldAcc);
			dao.save(oldAcc);
			return oldAcc;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	@PostMapping("/change/password")
	public Object changePass(String oldPass,String newPass) {
		try {
			
			account acc = session.getAttribute("acc");
			account oldAcc = dao.findById(acc.getId()).get();
			
			if(!oldAcc.getPassword().equalsIgnoreCase(oldPass)) {
				return false;
			}
			oldAcc.setPassword(newPass);
			dao.save(oldAcc);
			session.setAttribute("acc", oldAcc);
			return oldAcc;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	

	@PostMapping("/xacThuc")
	public Integer xacThuc(String gmail) {
		try {
			Random dn = new Random();
			int x = dn.nextInt(9999);
			int kq = 100000;
			kq = kq + x;
			String mes = "Ma Xac Thuc : " + kq;
			MimeMessage mess = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mess, true, "utf-8");
			helper.setFrom("Zay Shop <duchotboy9@gmail.com>");
			helper.setTo(gmail);
			helper.setSubject("Xac Thuc Tai Khoan");
			helper.setText(mes);
			sender.send(mess);
			return kq;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sai");
			return null;
		}
		
	}
	@PostMapping("/onAccount/{id}")
	public boolean OnAcc(@PathVariable("id") Integer id) {
		
		try {
			System.out.println("is id : " +id);
			account acc = dao.findById(id).get();
			acc.setActivated(true);
			dao.save(acc);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
	}
	
	
	
	
}
