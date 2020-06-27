package com.mitrais.test.service;

import com.mitrais.test.model.User;

public interface UserService {
	void saveUser(User user);
	
	User getUserByMobile(String mobile);
	
	User getUserByEmail(String email);
}
