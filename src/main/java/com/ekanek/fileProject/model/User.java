package com.ekanek.fileProject.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator="sequence" , strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name ="sequence" , allocationSize = 10)
    @Getter
    @Setter
    private Long id;
    
    @Email
	@NotBlank
	@Column(unique = true)
	@Getter
	@Setter
    private String email;
	
	@NotBlank
	@Getter
	@Setter
    private String saltedHashedPassword;
	
	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.EAGER)
	Set<Role> roles = new HashSet<>();

	/*
	 * public void setSaltedHashedPassword(String saltedHashedPassword) {
	 * this.saltedHashedPassword = new
	 * BCryptPasswordEncoder(5).encode(saltedHashedPassword); }
	 */

	public User(User user) { // copy constructor
		email = user.email;
		saltedHashedPassword = user.getSaltedHashedPassword();
		roles = user.getRoles();
	}
	
	public User() {}


}