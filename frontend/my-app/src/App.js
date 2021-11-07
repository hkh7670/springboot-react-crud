import React, { Fragment, useEffect, useState } from "react";
import './App.css';
import { boardApi } from "./api/BoardApi";


function App() {

    const [searchParams, setSearchParams] = useState({ page: 0, size: 5, title: '', content: '' });
    const [listInfo, setListInfo] = useState({ totalCnt: 0, value: { content: [] } })
    const [totalPagesValues, setTotalPagesValues] = useState([]);
    const [pageCnt, setPageCnt] = useState(5);

    useEffect(() => {
        getPostList();
    }, [])

    useEffect(() => {
        const totalPagesValues = [];
        for (let i = 0; i < listInfo.value.totalPages; i++) {
            totalPagesValues.push(i);
        }
        setTotalPagesValues(totalPagesValues);
        console.log("listInfo : ", listInfo);
    }, [listInfo])

    useEffect(() => {
        console.log("totalPagesValues : ", totalPagesValues);
    }, [totalPagesValues])

    useEffect(() => {
        getPostList();
    }, [searchParams.page, searchParams.size])

    function setPages(page) {
        setSearchParams({
            ...searchParams,
            page: page
        })
    }

    function setListSize(e) {
        setSearchParams({
            ...searchParams,
            size: e.target.value,
            page: 0
        })
    }

    function handleSearchParams(e) {
        const { name, value } = e.target;
        setSearchParams({
            ...searchParams,
            [name]: value,
        })
    }

    function getPostList() {
        boardApi.getPostList(searchParams).then((res) => {
            console.log("res: ", res);
            setListInfo({ value: res.data, totalCnt: res.data.totalElements });
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    function handleFormSubmit() {
        const _searchParams = searchParams;
        _searchParams.page = 0;
        boardApi.getPostList(_searchParams).then((res) => {
            console.log("res: ", res);
            setListInfo({ value: res.data, totalCnt: res.data.totalElements });
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    function handleEnterKeyPress(e) {
        if (e.key === 'Enter') {
            handleFormSubmit();
        }
    }

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

    return (
        <div>
            <div>Total!: {listInfo.totalCnt}</div>
            <span>Page Size </span>
            <select name="size" value={searchParams.size} onChange={(e) => setListSize(e)}>
                <option value={5}>5</option>
                <option value={10}>10</option>
                <option value={20}>20</option>
            </select>
            <table border="1">
                <th>No.</th>
                <th>USER ID</th>
                <th>TITLE</th>
                <th>CONTENT</th>
                <th>REG DATE</th>
                <th>UPT DATE</th>
                {listInfo.value.content.map((item, i) => {
                    return (
                        <Fragment>
                            <tr>
                                <td>{i + 1}</td>
                                <td>{item.userId}</td>
                                <td>{item.title}</td>
                                <td>{item.content}</td>
                                <td>{item.regDate}</td>
                                <td>{item.uptDate}</td>
                            </tr>
                        </Fragment>
                    )
                })}
            </table>
            <div>
                <button className={"pointer"} onClick={() => handlePageMove("prevEnd")}>{" << "}</button>
                <button className={"pointer"} onClick={() => handlePageMove("prev")}>{" < "}</button>
                {
                    totalPagesValues.map((item, i) => {
                        if(parseInt(i / pageCnt) === parseInt(searchParams.page / pageCnt)) {
                            if (i === searchParams.page) {
                                return <Fragment><span className={"pointer"} style={{color: "red"}} onClick={() => setPages(i)}>{i + 1} </span></Fragment>
                            }
                            return <Fragment><span className={"pointer"} onClick={() => setPages(i)}>{i + 1} </span></Fragment>
                        }
                    })
                }
                <button className={"pointer"} onClick={() => handlePageMove("next")}>{" > "}</button>
                <button className={"pointer"} onClick={() => handlePageMove("nextEnd")}>{" >> "}</button>

            </div>
            <div>
                <input name="title" onChange={(e) => handleSearchParams(e)} onKeyPress={(e) => handleEnterKeyPress(e)}/>
                <button onClick={handleFormSubmit}>조회</button>
            </div>
        </div>
    );
}

export default App;
