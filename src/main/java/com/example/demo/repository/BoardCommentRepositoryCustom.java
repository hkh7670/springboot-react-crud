package com.example.demo.repository;

import com.example.demo.dto.BoardCommentDto;
import com.example.demo.entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepositoryCustom  {
    List<BoardCommentDto> findByPostIdAndParentCommentIdIsNull(Long postId);

}
