package com.architechz.project.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.architechz.project.models.User;
import com.architechz.project.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(mail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el siguiente email: " + mail));

		return UserDetailsImpl.build(user);
	}
}
