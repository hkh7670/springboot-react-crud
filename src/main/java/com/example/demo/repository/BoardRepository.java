package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardRepositoryCustom {

    List<BoardEntity> findByTitle(String title);

    List<BoardEntity> findTop3ByTitle(String title);


    @Query("SELECT b FROM BoardEntity b left join fetch b.commentList where b.id = :seq")
    BoardEntity findPostOne(Long seq);
    @Query("SELECT distinct b FROM BoardEntity b left join fetch b.commentList where b.id in (:idList)")
    List<BoardEntity> findByIdIn(List<Long> idList);
}
