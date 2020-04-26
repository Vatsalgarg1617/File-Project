package com.ekanek.fileProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(generator="sequence" , strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name ="sequence" , allocationSize = 10)
    private Long id;
	
	@NotBlank
	@Getter
	@Setter
	private String name;
	
	@NotBlank
	@Getter
	@Setter
	private String description;
}
