import React, {Component} from 'react';
import Row from 'react-bootstrap/Row';
import TopBar from './topbar.js';
import NavigationBar from './navigation-bar.js';
import '../styling.css';
 
export default class Home extends Component {

  render() {

    return (
            <div className="container">
              <Row>
                <TopBar></TopBar>
              </Row>

              <Row>
                <NavigationBar></NavigationBar>
              </Row>
            </div>
    )

  }


}