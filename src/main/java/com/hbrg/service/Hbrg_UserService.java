package com.hbrg.service;

import com.hbrg.entity.Hbrg_User;
import com.hbrg.repository.Hbrg_UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class Hbrg_UserService {

    private final Hbrg_UserRepository hbrg_UserRepository;

    public Hbrg_User saveHbrg_User(Hbrg_User hbrg_User){
        validateDuplicateHbrg_User(hbrg_User);
        return hbrg_UserRepository.save(hbrg_User);
    }

    //이미 가입된 회원 이메일로 검사
    private void validateDuplicateHbrg_User(Hbrg_User hbrg_User){
        Hbrg_User findHbrg_User = hbrg_UserRepository.findByEm(hbrg_User.getEm());
        if (findHbrg_User != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}