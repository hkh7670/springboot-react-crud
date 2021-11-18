import React, { Fragment, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { boardApi } from "../api/BoardApi";
import { boardCommentApi } from "../api/BoardCommentApi";
import { commonUtil } from "../common/CommonUtil";


function BoardView() {
    const { seq } = useParams();
    const [post, setPost] = useState({ commentList: [] });
    const [comment, setComment] = useState({ postId: seq });

    useEffect(async () => {
        /*const commentList = await boardApi.getCommentList(seq);
        setCommentList(commentList.data);*/
        boardApi.getPostOne(seq).then((res) => {
            console.log("res: ", res);
            setPost(res.data);
        }).catch((err) => {
            console.log("error: ", err);
        })
    }, [])

    function handleSearchParams(e) {
        const { name, value } = e.target;
        setComment({
            ...comment,
            [name]: value,
            depth: 1,
        })
    }

    function insertComment() {
        if (commonUtil.isEmpty(comment.content)) {
            alert("댓글을 입력 후 추가버튼을 클릭해주세요.");
            return;
        }

        boardCommentApi.insertComment(comment).then((res) => {
            boardApi.getPostOne(seq).then((res) => {
                setPost(res.data);
                setComment({ postId: seq, content: "" });
            }).catch((err) => {
                console.log("error: ", err);
            })
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    return (
        <Fragment>
            <h2>제목 : {post.title}</h2>
            <h2>내용 : {post.content}</h2>
            <h2>댓글</h2>
            <ol>
                {post.commentList != null && post.commentList.map((item, i) => {
                    return (
                        <li>
                            {item.content} {"(" + item.regDate + ")"}
                        </li>
                    )
                })}
            </ol>
            <div>
                댓글 쓰기 : <input name={"content"} value={comment.content} onChange={handleSearchParams}/>
                <button onClick={insertComment}>댓글 추가</button>
            </div>
        </Fragment>
    )
}

export default BoardView