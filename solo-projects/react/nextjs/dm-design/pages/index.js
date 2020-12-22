import React, {Component} from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
 
export default class Home extends React.Component {

  render() {

    return (<Container>
      <Row>
        <Col>
          <h1>Next.js React Bootstrap</h1>
        </Col>
      </Row>
    </Container>)

  }


}