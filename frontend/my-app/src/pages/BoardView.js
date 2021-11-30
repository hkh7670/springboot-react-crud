import React, { Fragment, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { BoardApi } from "../api/BoardApi";
import { BoardCommentApi } from "../api/BoardCommentApi";
import { commonUtil } from "../common/CommonUtil";


function BoardView() {
    const { seq } = useParams();
    const [post, setPost] = useState({
        commentList: [
            { childCommentList: [] }
        ]
    });
    const [comment, setComment] = useState({ postId: seq, content: '' });

    useEffect(async () => {
        /*const commentList = await boardApi.getCommentList(seq);
        setCommentList(commentList.data);*/
        BoardApi.getPostOne(seq).then((res) => {
            console.log("res: ", res);
            setPost(res.data);
        }).catch((err) => {
            console.log("error: ", err);
        })
    }, [])

    useEffect(() => {
        console.log("post: ", post);
    }, [post])

    function handleSearchParams(e) {
        const { name, value } = e.target;
        setComment({
            ...comment,
            [name]: value,
        })
    }

    function insertComment() {
        if (commonUtil.isEmpty(comment.content)) {
            alert("댓글을 입력 후 추가버튼을 클릭해주세요.");
            return;
        }

        BoardCommentApi.insertComment(comment).then((res) => {
            BoardApi.getPostOne(seq).then((res) => {
                setPost(res.data);
                setComment({ postId: seq, content: "" });
            }).catch((err) => {
                console.log("error: ", err);
            })
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    function insertChildCommentToggle(id, childCommentInput) {
        let _post = post;
        let toggle;
        if(!!childCommentInput) {
            toggle = false;
        }
        else {
            toggle = true;
        }
        for (let i = 0; i < _post.commentList.length; i++) {
            if(_post.commentList[i].id === id) {
                _post.commentList[i].childCommentInput = toggle;
                break;
            }
        }
        setPost({ ..._post });
    }

    function insertChildComment(id) {
        console.log(id);
        const content = document.getElementById(id).value;
        console.log(content);
        if (commonUtil.isEmpty(content)) {
            alert("댓글을 입력 후 추가버튼을 클릭해주세요.");
            return;
        }
        const param = {
            postId: seq,
            content: content,
            parentCommentId: id
        }

        BoardCommentApi.insertComment(param).then((res) => {
            BoardApi.getPostOne(seq).then((res) => {
                setPost(res.data);
                setComment({ postId: seq, content: "" });
            }).catch((err) => {
                console.log("error: ", err);
            })
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    function getChildCommentList(id, isRenderedChildComment) {
        let _post = post;
        if (!!isRenderedChildComment) {
            for (let i = 0; i < _post.commentList.length; i++) {
                if(_post.commentList[i].id === id) {
                    _post.commentList[i].isRenderedChildComment = false;
                    break;
                }
            }
            setPost({ ..._post });
        }
        else {
            BoardCommentApi.getChildCommentList(id).then((res) => {
                for (let i = 0; i < _post.commentList.length; i++) {
                    if (_post.commentList[i].id === id) {
                        let childCommentList = [];
                        res.data.forEach((childComment) => {
                            childCommentList.push(childComment);
                        })
                        _post.commentList[i].childCommentList = childCommentList;
                        _post.commentList[i].isRenderedChildComment = true;
                        // console.log("_post: ", _post);
                        break;
                    }
                }
                setPost({ ..._post });
            }).catch((err) => {
                console.log("error: ", err);
            })
        }
    }

    return (
        <Fragment>
            <h2>제목 : {post.title}</h2>
            <h2>내용 : {post.content}</h2>
            <h2>댓글</h2>
            <ul>
                {post.commentList && post.commentList.map((item, i) => {
                    return (
                        <>
                            <li>
                                {item.content} {"(" + item.regDate + ")"} <span className={"comment"} onClick={() => insertChildCommentToggle(item.id, item.childCommentInput)}>{"(대댓글)"}</span>
                                {item.childCommentInput &&
                                    <>
                                        <input id={item.id} />
                                        <button onClick={(e) => insertChildComment(item.id)}>댓글 추가</button>
                                    </>
                                }
                            </li>
                            {item.childCommentCnt > 0 &&
                            <span className={"comment"}
                                  onClick={() => getChildCommentList(item.id, item.isRenderedChildComment)}>
                                {item.isRenderedChildComment ?
                                    <div>{"대댓글 숨기기 (" + item.childCommentCnt + ")"}</div>
                                    : <div>{"대댓글 보기 (" + item.childCommentCnt + ")"}</div>
                                }
                            </span>
                            }
                            <ul>
                                {(item.childCommentList && item.childCommentList.length > 0 && item.isRenderedChildComment) &&
                                    item.childCommentList.map((childComment) => {
                                        return (
                                            <>
                                                <li>{childComment.content + " (" + childComment.regDate + ")"}</li>
                                            </>
                                        )
                                    })
                                }
                            </ul>
                        </>
                    )
                })}
            </ul>
            <div>
                댓글 쓰기 : <input name={"content"} value={comment.content} onChange={handleSearchParams}/>
                <button onClick={insertComment}>댓글 추가</button>
            </div>
        </Fragment>
    )
}

export default BoardView