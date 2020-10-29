
import React from "react";
import {Nav, Navbar} from 'react-bootstrap';
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";

export  default  class Meniu extends React.Component{

    render(){
        return (<Navbar bg="primary" variant="dark">
            <Navbar.Brand href="#home">Medical Platform</Navbar.Brand>
            <Nav className="mr-auto">
                <Nav.Link href="user/home">Home</Nav.Link>
                <Nav.Link href="/logout">Logout</Nav.Link>
            </Nav>
            {/*<Form inline>*/}
            {/*    <FormControl type="text" placeholder="Search" className="mr-sm-2" />*/}
            {/*    <Button variant="outline-light">Search</Button>*/}
            {/*</Form>*/}
        </Navbar>

        );

    }

  }