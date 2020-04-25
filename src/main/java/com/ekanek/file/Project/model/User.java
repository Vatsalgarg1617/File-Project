package com.ekanek.file.Project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	@Getter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private String password;
}
