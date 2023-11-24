package com.njifanda.project.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.njifanda.project.Dtos.LoginDto;
import com.njifanda.project.Models.User;
import com.njifanda.project.Repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {

		Optional<User> findUser = this.userRepository.findById(id);
		if (findUser.isPresent()) {
			return (User) findUser.get();
		}

		return null;
	}
	
	public User register(User newUser, BindingResult result) {

		String password = newUser.getPassword();
    	if(!password.equals(newUser.getConfirm())) {

    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}

    	User verifyEmail = this.userRepository.findByEmail(newUser.getEmail());
    	if (verifyEmail != null) {

    		result.rejectValue("email", "Exist", "The Email already exist in the system!");
    	}
    	
    	if(result.hasErrors()) {

    	    return null;
    	}

    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return this.userRepository.save(newUser);
	}

	public User login(LoginDto login, BindingResult result) {

    	User user = this.userRepository.findByEmail(login.getEmail());
    	if (user != null) {

    		String password = login.getPassword();
    		String userHashedPassword = user.getPassword();
    		if (BCrypt.checkpw(password, userHashedPassword)) {

    			return user;
    		}
    	}
 
		result.rejectValue("email", "Incorrect", "Incorrect credentials");
		result.rejectValue("password", "Incorrect", "Incorrect credentials!");
		
		return null;
	}
}
