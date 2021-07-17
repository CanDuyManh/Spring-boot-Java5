package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.product;

public interface productDAO extends JpaRepository<product, Integer> {

}
