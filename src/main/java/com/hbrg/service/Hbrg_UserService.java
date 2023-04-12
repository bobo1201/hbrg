package com.hbrg.service;

import com.hbrg.entity.Hbrg_User;
import com.hbrg.repository.Hbrg_UserRepository;
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
public class Hbrg_UserService implements UserDetailsService {

    private final Hbrg_UserRepository hbrg_UserRepository;

    public Hbrg_User saveHbrg_User(Hbrg_User hbrg_User){
        validateDuplicateHbrg_User(hbrg_User);
        return hbrg_UserRepository.save(hbrg_User);
    }

    //이미 가입된 회원 이메일로 검사
    private void validateDuplicateHbrg_User(Hbrg_User hbrg_User){
        Hbrg_User findHbrg_User = hbrg_UserRepository.findById(hbrg_User.getId());
        if (findHbrg_User != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        Hbrg_User hbrg_user = hbrg_UserRepository.findById(id);
        if(hbrg_user==null){
            throw new UsernameNotFoundException(id);
        }

        return User.builder()
                .username(hbrg_user.getEm())
                .password(hbrg_user.getPw())
                .build();
    }
}