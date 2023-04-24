package com.hbrg.repository;

import com.hbrg.entity.Hfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Hfile, Long> {

    Hfile findByFileId(Long fileId);

}
