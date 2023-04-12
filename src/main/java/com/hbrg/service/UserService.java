package com.hbrg.service;

import com.hbrg.entity.HUser;
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

    public HUser saveHUser(HUser hUser){
        validateDuplicateHUser(hUser);
        return userRepository.save(hUser);
    }

    //이미 가입된 회원 이메일로 검사
    private void validateDuplicateHUser(HUser hUser){
        HUser findhUser = userRepository.findById(hUser.getId());
        if (findhUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        HUser hUser = userRepository.findById(id);
        if(hUser==null){
            throw new UsernameNotFoundException(id);
        }

        return User.builder()
                .username(hUser.getId())
                .password(hUser.getPw())
                .roles(hUser.getRole().toString())
                .build();
    }
}