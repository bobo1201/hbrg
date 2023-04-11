package com.hbrg.repository;

import com.hbrg.entity.Hbrg_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Hbrg_UserRepository extends JpaRepository<Hbrg_User, Long> {
    //아이디로 중복된 회원인지 검사
    Hbrg_User findById(String id);
}