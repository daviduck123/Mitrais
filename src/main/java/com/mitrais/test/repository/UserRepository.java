package com.mitrais.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.test.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByMobile(String mobile);

	User findByEmail(String email);

}
