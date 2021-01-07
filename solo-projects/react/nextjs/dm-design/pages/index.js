import React, {Component} from 'react';
import Row from 'react-bootstrap/Row';
import TopBar from './topbar.js';
import Col from 'react-bootstrap/Col';
import '../styling.css';
 
export default class Home extends Component {

  render() {

    return (<div class="container">
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