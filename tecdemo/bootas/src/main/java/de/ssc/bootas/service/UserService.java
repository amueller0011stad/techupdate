package de.ssc.bootas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.ssc.bootas.model.User;
import de.ssc.bootas.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User save(User user){
		if(null == user.getId()){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}else{
			User existingUser = userRepository.findOne(user.getId());
			user.setPassword(existingUser.getPassword());
		}
		return userRepository.saveAndFlush(user);
	}	

}
