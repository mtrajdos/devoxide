import React, {Component} from 'react';
import Row from 'react-bootstrap/Row';
import TopBar from './topbar.js';
import '../styling.css';
 
export default class Home extends Component {

  render() {

    return (<div className="container">
      <Row>
          <h1>Home component</h1>
      </Row>
      <Row>
          <TopBar></TopBar>
      </Row>
      </div>
    )

  }


}