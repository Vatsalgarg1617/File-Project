package com.ekanek.fileProject.repositories;

import org.springframework.stereotype.Repository;

import com.ekanek.fileProject.model.DBFile;
import com.ekanek.fileProject.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
	
	List<DBFile> findAllByOwner(User Owner);
	
	

}
