import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import * as React from "react";
import {Nav} from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";

export  default  class MeniuCaregiver extends React.Component{

    render(){
        return (<Navbar bg="primary" variant="dark">
                <Navbar.Brand href="#home">Medical Platform</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href='/caregiver/viewMyPatients'>My Pacients</Nav.Link>
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