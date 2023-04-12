package com.hbrg.repository;

import com.hbrg.entity.HUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<HUser, Long> {

}
