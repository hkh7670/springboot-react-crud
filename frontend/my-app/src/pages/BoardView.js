import React, { Fragment, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { boardApi } from "../api/BoardApi";


function BoardView() {
    const { seq } = useParams();
    const [content, setContent] = useState("");
    useEffect(() => {
        boardApi.getPostOne(seq).then((res) => {
            setContent(res.data.content);
        }).catch((err) => {
            console.log("error: ", err);
        })
    }, [])
    return (
        <Fragment>
            <h2>{content}</h2>
        </Fragment>
    )
}

export default BoardView