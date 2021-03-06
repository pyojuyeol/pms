import React, {Component} from 'react';
import {SpreadSheets, Worksheet, Column} from '@grapecity/spread-sheets-react';
import './Style.css'
import dataService from '../dataService'

class DataBingingCon extends Component {

    constructor(props) {
        super(props);
        this.hostStyle = {
            top: '90px',
            bottom: '0px'
        };
        this.autoGenerateColumns = false;
        this.data = dataService.getAirpotsData();
    }

    render() {
        return (
            <div className="componentContainer" style={this.props.style}>
                <h3>데이터 바인딩</h3>
                <div>
                    <p>래 샘플은 데이터 바인딩을 사용하는 방법을 보여줍니다.</p>
                </div>
                <div className="spreadContainer" style={this.hostStyle}>
                    <SpreadSheets>
                        <Worksheet dataSource = {this.data} name = "All Data"/>
                        <Worksheet dataSource = {this.data}  name="Part Data" autoGenerateColumns={this.autoGenerateColumns}>
                            <Column dataField="name" headerText="Name"/>
                            <Column dataField="city" headerText="City"/>
                            <Column dataField="state" headerText="State"/>
                            <Column dataField="lat" headerText="Lat"/>
                            <Column dataField="lon" headerText="Lon"/>
                            <Column dataField="vol2011" headerText="Vol2011"/>
                        </Worksheet>
                    </SpreadSheets>
                </div>
            </div>

        )
    }
}

export default DataBingingCon