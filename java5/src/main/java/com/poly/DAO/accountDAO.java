package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.account;

public interface accountDAO extends JpaRepository<account,Integer> {

	public List<account> findByUserName(String userName);
	
}
