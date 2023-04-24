package com.hbrg.repository;

import com.hbrg.entity.Huser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Huser, Long> {

    Huser findById(String id);

    <S extends Huser> S findByEmail(String email);
}
