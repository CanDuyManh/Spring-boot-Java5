package com.poly.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "account")
public class account implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private java.lang.String userName;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String email;

	@Column
	private String photo;
	
	@Column
	private boolean activated;
	
	@Column
	private boolean admin;
	
	@OneToMany(mappedBy = "account" , fetch = FetchType.LAZY)
	List<order> orders;

	
	
	
	
	

}
