package com.moviereview.service;


import com.moviereview.model.User;
import com.moviereview.repo.UserRepo;
import com.moviereview.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException("No user with username: " + s));
        return new UserDetailsImpl(user);
    }
}
