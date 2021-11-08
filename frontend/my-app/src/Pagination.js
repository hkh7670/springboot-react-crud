import React, { Fragment, useState } from "react";

function Pagination(props) {
    const [pageCnt, setPageCnt] = useState(5);
    function handlePageMove(type) {
        let page;
        if(props.totalPagesValues.length <= 0) {
            page = 0;
        }
        else {
            switch (type){
                case "prevEnd":
                    page = 0;
                    break;
                case "prev":
                    page = props.searchParams.page === 0 ? 0 : props.searchParams.page - 1;
                    break;
                case "next":
                    page = props.searchParams.page === props.totalPagesValues.length - 1 ? props.totalPagesValues.length - 1 : props.searchParams.page + 1;
                    break;
                case "nextEnd":
                    page = props.totalPagesValues.length - 1;
                    break;
                default:
                    page = 0;
                    break;
            }
        }
        props.setSearchParams({
            ...props.searchParams,
            page: page
        })
    }

    function setPages(page) {
        props.setSearchParams({
            ...props.searchParams,
            page: page
        })
    }

    return (
        <div>
            <button className={"pointer"} onClick={() => handlePageMove("prevEnd")}>{"<<"}</button>
            <button className={"pointer"} onClick={() => handlePageMove("prev")}>{"<"}</button>
            {
                props.totalPagesValues.map((item, i) => {
                    if (parseInt((i / pageCnt).toString()) === parseInt((props.searchParams.page / pageCnt).toString())) {
                        if (i === props.searchParams.page) {
                            return <Fragment><span className={"pointer"} style={{ color: "red" }}
                                                   onClick={() => setPages(i)}>{i + 1} </span></Fragment>
                        }
                        return <Fragment><span className={"pointer"}
                                               onClick={() => setPages(i)}>{i + 1} </span></Fragment>
                    }
                })
            }
            <button className={"pointer"} onClick={() => handlePageMove("next")}>{">"}</button>
            <button className={"pointer"} onClick={() => handlePageMove("nextEnd")}>{">>"}</button>

        </div>
    )
}

export default Pagination