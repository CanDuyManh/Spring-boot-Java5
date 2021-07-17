package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.account;
import com.poly.model.order;

public interface orderDAO extends JpaRepository<order,Integer> {
	
	List<order> findByAccount(account account);

}
