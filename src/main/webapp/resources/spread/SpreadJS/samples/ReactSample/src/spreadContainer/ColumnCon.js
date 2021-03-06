import React, {Component} from 'react';
import {SpreadSheets, Worksheet, Column} from '@grapecity/spread-sheets-react';
import './Style.css'

class ColumnCon extends Component {

    constructor(props) {
        super(props);
        this.state = {
            visible: true,
            resizable: true,
            width: 300,
            formatter: '$ #.00'
        };
        this.hostStyle = {
            top: '90px',
            bottom: '74px'
        };
        this.autoGenerateColumns = false;
        let dataTable = [];
        for (let i = 0; i < 42; i++) {
            dataTable.push({price: i + 0.56})
        }
        this.data = dataTable;
    }

    changeProps(props, value) {
        let state = {};
        state[props] = value;
        this.setState(state);
    }

    render() {
        return (
            <div className="componentContainer" style={this.props.style}>
                <h3>GC-Column</h3>
                <div>
                    <p>아래 샘플은 열의 일부 속성을 바인딩하는 방법을 보여줍니다.</p>
                </div>
                <div className="spreadContainer" style={this.hostStyle}>
                    <SpreadSheets>
                        <Worksheet dataSource={this.data} autoGenerateColumns={this.autoGenerateColumns}>
                            <Column dataField="price" width={this.state.width} formatter = {this.state.formatter} visible = {this.state.visible} resizable={this.state.resizable}/>
                        </Worksheet>
                    </SpreadSheets>
                </div>
                <div className="settingContainer">
                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <label><input type="checkbox" checked={this.state.visible} onChange={(e) => {this.changeProps('visible', e.target.checked)}}/>표시</label>
                            </td>
                            <td>
                                <label><input type="checkbox" checked={this.state.resizable} onChange={(e) => {this.changeProps('resizable', e.target.checked)}}/>크기 조정 가능</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label><input type="text" value={this.state.width} onChange={(e) => {this.changeProps('width', e.target.value)}}/>너비</label>
                            </td>
                            <td>
                                <label><input type="text" value={this.state.formatter} onChange={(e) => {this.changeProps('formatter', e.target.value)}}/>포맷터</label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        )
    }
}

export default ColumnCon