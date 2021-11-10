import React, { Fragment } from "react";

function TableContents(props) {
    const { content, tableHeaderLength } = props;
    return (
        <Fragment>
            {content.map((item, i) => {
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
            {content.length === 0 && <tr>
                <td className={"text_center"} colSpan={tableHeaderLength}>No Result</td>
            </tr>}
        </Fragment>
    )
}

export default TableContents