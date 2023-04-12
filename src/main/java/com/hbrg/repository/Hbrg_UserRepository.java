package com.hbrg.repository;

import com.hbrg.entity.Hbrg_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Hbrg_UserRepository extends JpaRepository<Hbrg_User, Long> {

    Hbrg_User findById(String id);
}
