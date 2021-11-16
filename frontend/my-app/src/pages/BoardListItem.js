import React, { Fragment } from "react";
import { Link, Route } from "react-router-dom";
import BoardView from "./BoardView"

function BoardListItem(props) {
    const { content, tableHeaderLength } = props;
    return (
        <Fragment>
            {content.map((item, i) => {
                return (
                    <Fragment>
                        <tr>
                            <td>{i + 1}</td>
                            {/*<td>{item.title}</td>*/}
                            <Link to={{pathname: `/view/${item.id}`}}>{item.title}</Link>
                            {/*<td>{item.content}</td>*/}
                            <td>{item.userId}</td>
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

export default BoardListItem