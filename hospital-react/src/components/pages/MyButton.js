
import {Button} from "react-bootstrap";
import React, { Component } from 'react';
import {Link,BrowserRouter as Router} from "react-router-dom";

export  default class MyButton extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            patient: {
                id: '',
                name: '',
                birthDate: '',
                gender: '',
                adress: '',
                username: '',
                password: '',
                role: ''
            }
        }
        this.setState({patient: this.props.patient})
    }

    routeChange() {
        localStorage.setItem("patient", this.state.patient)
        window.location.href = "http://localhost:3000/patient/medicationplans"
    }

    routeChange1() {
        window.location.href = "http://localhost:3000/caregiver/caregiverpacients"
    }

    render() {
        if (this.props.patient.role === "PATIENT") {
            return (

                <Button className="my-3" color="primary" onClick={this.routeChange()}>Add Patient</Button>

            )
        } else {
            return (

                <div>

                </div>
            )
        }

    }
}