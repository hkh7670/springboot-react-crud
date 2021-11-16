package com.example.demo.repository;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardRepositoryCustom {

    List<BoardEntity> findByTitle(String title);

    List<BoardEntity> findTop3ByTitle(String title);

}
