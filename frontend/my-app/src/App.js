import React, { Fragment, useEffect, useState } from "react";
import './App.css';
import { boardApi } from "./api/BoardApi";


function App() {

    const [searchParams, setSearchParams] = useState({ page: 0, size: 5, title: '', content: '' });
    const [listInfo, setListInfo] = useState({ totalCnt: 0, value: { content: [] } })
    const [totalPagesValues, setTotalPagesValues] = useState([]);

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

    return (
        <div>
            <div>Total: {listInfo.totalCnt}</div>
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
                {totalPagesValues.map((item, i) => {
                    return (
                        <Fragment>
                            <span onClick={() => setPages(i)}>{i + 1} </span>
                        </Fragment>
                    )
                })}
            </div>
            <div>
                <input name="title" onChange={(e) => handleSearchParams(e)} onKeyPress={handleEnterKeyPress}/>
                <button onClick={handleFormSubmit}>조회</button>
            </div>
        </div>
    );
}

export default App;
