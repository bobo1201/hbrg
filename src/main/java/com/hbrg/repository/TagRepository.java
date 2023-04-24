package com.hbrg.repository;

import com.hbrg.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByTagIdIn(List<Long> tagId);

    Tag findByTagNm(String tagNm);
}
