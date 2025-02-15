package com.apirest.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.crud.model.user;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

}
