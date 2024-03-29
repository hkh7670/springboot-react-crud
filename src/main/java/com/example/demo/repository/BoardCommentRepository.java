package com.example.demo.repository;

import com.example.demo.entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long>, BoardCommentRepositoryCustom {
    List<BoardCommentEntity> findByParentCommentIdOrderByRegDateDesc(Long seq);

    List<BoardCommentEntity> findByPostId(Long id);
}
