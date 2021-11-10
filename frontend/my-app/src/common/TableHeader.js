import React from "react";

function TableHeader(props) {
    const { tableHeader } = props;
    return (
        Object.keys(tableHeader).map((key, index) => (
                <th>{tableHeader[key]["columnName"]}</th>
            )
        )
    )
}

export default TableHeader