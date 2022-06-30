import {Link} from 'react-dom';
import React, {Component} from 'react';
import { Navbar, Nav, Form, FormControl, Button } from 'react-bootstrap';

export default class NavigationBar extends Component {

    render() {

        return  (

            <div id="navigationBar">
            <Navbar bg="primary" variant="dark">
              <Navbar.Brand href="/">DM Design</Navbar.Brand>
              <Nav className="mx-auto">
                <Nav.Link href="/oferta">Oferta</Nav.Link>
                <Nav.Link href="/projekty">Projekty</Nav.Link>
                <Nav.Link href="/kontakt">Kontakt</Nav.Link>
              </Nav>
              <Form inline>
                <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                <Button variant="outline-light">Search</Button>
              </Form>
            </Navbar>
          </div>

                )

    }

}