import React from "react";
import {Nav, Navbar} from 'react-bootstrap';
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import Cookie from "js-cookie";

export  default  class Logout extends React.Component{

    routeChange(){
        window.location.href="http://localhost:3000/login"
    }

    render(){
        Cookie.set("user",null)
        Cookie.set("idUser",null)
        Cookie.set("role",null)
        this.props.changeAutentificat(false)

        return (
            <div>
                <h1>V-ati delogat cu succes</h1>
                <Button className="my-3" color="primary" onClick={this.routeChange()}>Login</Button>
            </div>

        );


    }

}