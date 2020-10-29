import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import * as React from "react";
import {Nav, Navbar} from 'react-bootstrap';

export  default  class MeniuAdmin extends React.Component{

    render(){
        return (<Navbar bg="primary" variant="dark">
                <Navbar.Brand href="/home">Medical Platform</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="/patient">Patients</Nav.Link>
                    <Nav.Link href="/drugs">Drugs</Nav.Link>
                    <Nav.Link href="/caregiver">Caregivers</Nav.Link>
                    <Nav.Link href="/logout">Logout</Nav.Link>
                </Nav>
            </Navbar>
        );

    }

}