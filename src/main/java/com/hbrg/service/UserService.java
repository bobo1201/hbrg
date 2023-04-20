package com.hbrg.service;

import com.hbrg.entity.Huser;
import com.hbrg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Huser saveUser(Huser user){
        validateDuplicateUser(user);
        validateDuplicateEm(user);
        return userRepository.save(user);
    }

    //이미 가입된 회원 이메일로 검사
    private void validateDuplicateUser(Huser user){
        Huser findUser = userRepository.findById(user.getId());
        if (findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    private void validateDuplicateEm(Huser user){
        Huser findUser = userRepository.findByEm(user.getEm());
        if (findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        Huser user = userRepository.findById(id);
        if(user==null){
            throw new UsernameNotFoundException(id);
        }

        return User.builder()
                .username(user.getId())
                .password(user.getPw())
                .roles(user.getRole().toString())
                .build();
    }
}