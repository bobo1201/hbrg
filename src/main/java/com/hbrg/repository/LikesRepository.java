package com.hbrg.repository;

import com.hbrg.entity.Board;
import com.hbrg.entity.Huser;
import com.hbrg.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Transactional
    void deleteByUserAndBoard(Huser user, Board board);

    Optional<Likes> findByUserAndBoard(Huser user, Board board);

}
