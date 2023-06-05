package com.example.spring_homework27.Service;

import com.example.spring_homework27.Model.MyUser;
import com.example.spring_homework27.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = authRepository.findMyUserByUsername(username);
        if (myUser == null){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;
    }
}
