package com.ekanek.fileProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ekanek.fileProject.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
