package com.example.demo.repository;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.BoardEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.demo.entity.QBoardEntity.boardEntity;

@Repository
public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(BoardEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PageImpl<BoardDto> getBoardList(BoardDto boardDto, Pageable pageable) {
        JPAQuery<BoardDto> query = jpaQueryFactory
                .select(Projections.constructor
                        (BoardDto.class, boardEntity.id, boardEntity.title, boardEntity.content, boardEntity.userId, boardEntity.regDate, boardEntity.uptDate))
                .from(boardEntity)
                .where(likeTitle(boardDto.getTitle()),
                        likeContent(boardDto.getContent()),
                        eqId(boardDto.getId())
                )
                .orderBy(boardEntity.regDate.desc(), boardEntity.title.desc());
        return new PageImpl<>(getQuerydsl().applyPagination(pageable, query).fetch(), pageable, query.fetchCount());
    }

    @Override
    public PageImpl<BoardDto> findByTitleByQueryDsl(BoardDto boardDto, Pageable pageable) {
        JPAQuery<BoardDto> query = jpaQueryFactory
                .select(Projections.constructor
                        (BoardDto.class, boardEntity.id, boardEntity.title, boardEntity.content, boardEntity.userId, boardEntity.regDate, boardEntity.uptDate))
                .from(boardEntity)
                .where(likeTitle(boardDto.getTitle()),
                        eqId(boardDto.getId()))
                .offset(1)
                .limit(1);
        List<BoardDto> result = getQuerydsl().applyPagination(pageable, query).fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);


        /*return queryFactory
                .select(Projections.constructor
                        (PostDto.class, boardEntity.id, boardEntity.title, boardEntity.content, boardEntity.userId, boardEntity.regDate, boardEntity.uptDate))
                .from(boardEntity)
                .where(likeTitle(postDto.getTitle()),
                        eqId(postDto.getId()))
                .fetch();*/

    }

    public BooleanExpression likeTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return boardEntity.title.like("%" + title + "%");
    }

    public BooleanExpression likeContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return boardEntity.content.like("%" + content + "%");
    }

    public BooleanExpression eqId(Long seq) {
        if (StringUtils.isEmpty(seq)) {
            return null;
        }
        return boardEntity.id.eq(seq);
    }
}

