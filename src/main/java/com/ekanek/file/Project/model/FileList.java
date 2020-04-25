package com.ekanek.file.Project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="file_list")
public class FileList {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToOne
	@JoinColumn(name="OWNER_USER_ID")
	@JsonIgnore
	private User owner;
	
	public FileList(String name, User owner) {
	    this.name = name;
	    this.owner = owner;
	}
	
}
