package com.example.demo.repository;

import com.example.demo.dto.BoardCommentDto;
import com.example.demo.entity.QBoardCommentEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class BoardCommentRepositoryCustomImpl implements BoardCommentRepositoryCustom {

    private final QBoardCommentEntity boardCommentEntity = new QBoardCommentEntity("comment");
    private final QBoardCommentEntity boardChildCommentEntity = new QBoardCommentEntity("childComment");
    private final JPAQueryFactory jpaQueryFactory;

    public BoardCommentRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<BoardCommentDto> findByPostIdAndParentCommentIdIsNull(Long postId) {

        JPAQuery<BoardCommentDto> query = jpaQueryFactory
                .select(Projections.constructor
                        (BoardCommentDto.class, boardCommentEntity.id, boardCommentEntity.parentCommentId, boardCommentEntity.depth, boardCommentEntity.postId, boardCommentEntity.userId, boardCommentEntity.content, boardCommentEntity.regDate, boardCommentEntity.uptDate, boardChildCommentEntity.parentCommentId.count()))
                .from(boardCommentEntity)
                .leftJoin(boardChildCommentEntity).on(boardChildCommentEntity.parentCommentId.eq(boardCommentEntity.id).and(boardChildCommentEntity.parentCommentId.isNotNull()))
                .where(eqId(postId), boardCommentEntity.parentCommentId.isNull())
                .groupBy(boardCommentEntity.id, boardCommentEntity.parentCommentId, boardCommentEntity.depth, boardCommentEntity.postId, boardCommentEntity.userId, boardCommentEntity.content);
        return query.fetch();
    }

    public BooleanExpression eqId(Long seq) {
        if (StringUtils.isEmpty(seq)) {
            return null;
        }
        return boardCommentEntity.postId.eq(seq);
    }
}
