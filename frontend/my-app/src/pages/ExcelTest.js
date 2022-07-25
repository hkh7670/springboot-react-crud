import React, { Fragment } from "react";
import ExcellentExport from "excellentexport";

function ExcelTest() {

    const handleClickXLSX = (tableId, fileName) => {
        console.log("download button clicked.");
        return ExcellentExport.convert(
            { anchor: this, filename: fileName, format: "xlsx", openAsDownload: true },
            [{ name: "Sheet", from: { table: tableId } },]
        );
        /*return ExcellentExport.excel(this, table, 'Sheet');*/
    }

    return (
        <Fragment>
            <table id={"dataTable1"} border="1">
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>한놈</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>두시기</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>석삼</td>
                </tr>
            </table>

            <table id={"dataTable2"}>
                <tr>
                    <th rowSpan="2">이름</th>
                    <td>철수</td>
                </tr>
                <tr>
                    <td>영희</td>
                </tr>
            </table>

            <table id={"dataTable3"}>
                <caption>Lorem</caption>
                <thead>
                <tr>
                    <th></th>
                    <th>Ipsum</th>
                    <th>Ipsum</th>
                    <th>Ipsum</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Ipsum</th>
                    <td>Dolor</td>
                    <td>Dolor</td>
                    <td rowSpan="2">Dolor</td>
                </tr>
                <tr>
                    <th>Ipsum</th>
                    <td>Dolor</td>
                    <td>Dolor</td>
                </tr>
                <tr>
                    <th>Ipsum</th>
                    <td>Dolor</td>
                    <td>Dolor</td>
                    <td>Dolor</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colSpan="2">Table Foot</td>
                </tr>
                </tfoot>
            </table>

            <a onClick={() => handleClickXLSX("dataTable1", "주문목록")}>Export
                to CSV</a>
            {/*<a onClick="return ExcellentExport.convert(
            { anchor: this, filename: 'filename', format: 'xlsx' },
            [{ name: '주문목록', from: { table: 'dataTable3' } },]
        );">Export
                to CSV</a>*/}
        </Fragment>
    )
}

export default ExcelTest