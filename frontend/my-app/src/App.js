import React, { Fragment, useEffect, useRef, useState } from "react";
import './App.css';
import { BoardApi } from "./api/BoardApi";
import Pagination from "./common/Pagination";
import TableHeader from "./common/TableHeader";
import BoardListItem from "./pages/BoardListItem";
import BoardList from "./pages/BoardList";
import AppRoutes from "./common/AppRoutes";

function App() {
    return (
        <Fragment>
            <AppRoutes />
        </Fragment>

    );
}

export default App;
