import React, { useEffect, useRef, useState } from "react";
import '../App.css';
import { BoardApi } from "../api/BoardApi";
import Pagination from "../common/Pagination";
import TableHeader from "../common/TableHeader";
import BoardListItem from "./BoardListItem";

function BoardList() {

    const [searchParams, setSearchParams] = useState({ page: 0, size: 10, title: '', content: '' });
    const [listInfo, setListInfo] = useState({ totalCnt: 0, value: { content: [] } })
    const [totalPagesValues, setTotalPagesValues] = useState([]);
    const isMounted = useRef(false);

    const tableHeader = {
        No: { columnName: "No." },
        title: { columnName: "Title" },
        // content: { columnName: "Content" },
        userId: { columnName: "User ID" },
        regDate: { columnName: "Reg Date" },
        uptDate: { columnName: "Upt Date" },
    }


    useEffect(() => {
        const totalPagesValues = [];
        for (let i = 0; i < listInfo.value.totalPages; i++) {
            totalPagesValues.push(i);
        }
        setTotalPagesValues(totalPagesValues);
        console.log("listInfo : ", listInfo);
    }, [listInfo])

    useEffect(() => {
        console.log("tableHeader : ", tableHeader);
        if (isMounted.current) {
            getPostList();
        }
        else {
            isMounted.current = true;
        }
    }, [searchParams.page, searchParams.size])

    const setListSize = (e) => {
        setSearchParams({
            ...searchParams,
            size: e.target.value,
            page: 0
        })
    }

    const handleSearchParams = (e) => {
        const { name, value } = e.target;
        setSearchParams({
            ...searchParams,
            [name]: value,
        })
    }

    const getPostList = () => {
        BoardApi.getPostList(searchParams).then((res) => {
            console.log("res: ", res);
            setListInfo({ value: res.data, totalCnt: res.data.totalElements });
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    const handleFormSubmit = () => {
        const _searchParams = searchParams;
        _searchParams.page = 0;
        BoardApi.getPostList(_searchParams).then((res) => {
            console.log("res: ", res);
            setListInfo({ value: res.data, totalCnt: res.data.totalElements });
        }).catch((err) => {
            console.log("error: ", err);
        })
    }

    const handleEnterKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleFormSubmit();
        }
    }

    return (
        <div className={"center-align"}>
            <span>Page Size </span>
            <select name="size" value={searchParams.size} onChange={(e) => setListSize(e)}>
                <option value={3}>3</option>
                <option value={5}>5</option>
                <option value={10}>10</option>
                <option value={20}>20</option>
            </select>
            <div>Total: {listInfo.totalCnt}</div>
            <table border="2">
                <TableHeader tableHeader={tableHeader}/>
                <BoardListItem content={listInfo.value.content} tableHeaderLength={Object.keys(tableHeader).length}/>
            </table>
            <Pagination searchParams={searchParams} setSearchParams={setSearchParams}
                        totalPagesValues={totalPagesValues}/>
            <div style={{ marginTop: '10px' }}>
                <input style={{ marginRight: '10px' }} name="title" onChange={(e) => handleSearchParams(e)}
                       onKeyPress={(e) => handleEnterKeyPress(e)}/>
                <button onClick={handleFormSubmit}>조회</button>
            </div>
        </div>
    );
}

export default BoardList;
