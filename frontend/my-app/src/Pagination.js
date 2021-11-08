import React, { Fragment, useState } from "react";

function Pagination(props) {
    const [pageCnt, setPageCnt] = useState(5);
    const { totalPagesValues, searchParams, setSearchParams } = props;
    function handlePageMove(type) {
        let page;
        if(totalPagesValues.length <= 0) {
            page = 0;
        }
        else {
            switch (type){
                case "prevEnd":
                    page = 0;
                    break;
                case "prev":
                    page = searchParams.page === 0 ? 0 : searchParams.page - 1;
                    break;
                case "next":
                    page = searchParams.page === totalPagesValues.length - 1 ? totalPagesValues.length - 1 : searchParams.page + 1;
                    break;
                case "nextEnd":
                    page = totalPagesValues.length - 1;
                    break;
                default:
                    page = 0;
                    break;
            }
        }
        setSearchParams({
            ...searchParams,
            page: page
        })
    }

    function setPages(page) {
        setSearchParams({
            ...searchParams,
            page: page
        })
    }

    return (
        <div>
            <button className={"pointer"} onClick={() => handlePageMove("prevEnd")}>{"<<"}</button>
            <button className={"pointer"} onClick={() => handlePageMove("prev")}>{"<"}</button>
            {
                totalPagesValues.map((item, i) => {
                    if (parseInt((i / pageCnt).toString()) === parseInt((searchParams.page / pageCnt).toString())) {
                        if (i === searchParams.page) {
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