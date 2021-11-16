import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import BoardList from "../pages/BoardList";
import BoardView from "../pages/BoardView";

function AppRoutes() {
    return (
        <Routes>
            <Route path={"/"} element={<BoardList />}/>
            <Route path={"/view/:seq"} element={<BoardView />} />
        </Routes>
    )
}

export default AppRoutes;