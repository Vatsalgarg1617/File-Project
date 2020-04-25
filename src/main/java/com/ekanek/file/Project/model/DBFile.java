package com.ekanek.file.Project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "files")
public class DBFile {

	@Id
	@GeneratedValue
	private Long id;

	private String fileName;

	private String fileType;

	@OneToOne
	@JoinColumn(name = "OWNER_USER_ID")
	@JsonIgnore
	private User owner;

	@Lob
	private byte[] data;

	public DBFile(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

}
