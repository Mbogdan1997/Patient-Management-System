import React from 'react';
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";
import Neautentificat from "../Neautentificat";
import Meniu from "../Meniu";
import MeniuCaregiver from "../MeniuCaregiver";
import MeniuAdmin from "../MeniuAdmin";
import Cookie from "js-cookie";

export  default  class MeniuDiferentiat extends React.Component{

    render(){
       if(Cookie.get("role")==="DOCTOR")
            return (
                <MeniuAdmin>

                </MeniuAdmin>
            );
        else if(Cookie.get("role")==="CAREGIVER")
            return (
                <MeniuCaregiver>

                </MeniuCaregiver>
            );
        else if(Cookie.get("role")==="PATIENT")
            return (
                <Meniu>

                </Meniu>
            );
        else
            return (
                <Neautentificat>

                </Neautentificat>
            );

    }

}