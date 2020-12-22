import React, {Component} from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import '../styling.css';
 
export default class Home extends Component {

  render() {

    return (<div class="container">
      <Row>
        <Col>
          <h1>Next.js React Bootstrap</h1>
        </Col>
      </Row>
      </div>
    )

  }


}