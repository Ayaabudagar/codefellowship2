package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    DBUserRepository dbUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUser newApplicationUser = dbUserRepository.findByUsername(username);
        ApplicationUser applicationUser = new ApplicationUser(newApplicationUser);
        if(newApplicationUser == null){
            throw  new UsernameNotFoundException("The user "+ username + " does not exist");
        }
        return applicationUser;
    }


}
