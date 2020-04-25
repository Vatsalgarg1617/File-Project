package com.ekanek.fileProject.repositories;

import org.springframework.stereotype.Repository;

import com.ekanek.fileProject.model.DBFile;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
