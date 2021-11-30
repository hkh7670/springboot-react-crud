package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardCommentEntity is a Querydsl query type for BoardCommentEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardCommentEntity extends EntityPathBase<BoardCommentEntity> {

    private static final long serialVersionUID = -1755647059L;

    public static final QBoardCommentEntity boardCommentEntity = new QBoardCommentEntity("boardCommentEntity");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> parentCommentId = createNumber("parentCommentId", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> uptDate = _super.uptDate;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBoardCommentEntity(String variable) {
        super(BoardCommentEntity.class, forVariable(variable));
    }

    public QBoardCommentEntity(Path<? extends BoardCommentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardCommentEntity(PathMetadata metadata) {
        super(BoardCommentEntity.class, metadata);
    }

}

