package com.njifanda.project.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.project.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
}
