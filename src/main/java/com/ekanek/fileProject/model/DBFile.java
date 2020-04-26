package com.ekanek.fileProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
public class DBFile {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Getter	
    private String id;

	@Getter
	@Setter
    private String fileName;
	
	@Getter
	@Setter
    private String fileType;

    @Lob
    @Getter
	@Setter
    private byte[] data;
    
    @OneToOne
    @JoinColumn(name = "OWNER_USER_ID")
    @JsonIgnore
    private User owner;

    public DBFile() {}

    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
     //   this.owner = owner;
    }
    
    public DBFile(String fileName, User owner) {
    	this.fileName = fileName;
    	this.owner = owner;
    }

}
