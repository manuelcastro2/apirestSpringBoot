package com.apirest.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apirest.crud.model.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

}
