import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import * as React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";

export  default  class Neautentificat extends React.Component{

    render(){
        return (<Navbar bg="primary" variant="dark">
                <Navbar.Brand href="#home">Medical Platform</Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href='/login'>Login</Nav.Link>
                </Nav>
                {/*<Form inline>*/}
                {/*    <FormControl type="text" placeholder="Search" className="mr-sm-2" />*/}
                {/*    <Button variant="outline-light">Search</Button>*/}
                {/*</Form>*/}
            </Navbar>
        );

    }

}