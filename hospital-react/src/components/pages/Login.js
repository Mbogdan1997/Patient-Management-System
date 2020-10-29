import React, {Component} from "react";
import "../style/Login.css"
import axios from "axios";
import Cookie from "js-cookie";
import SockJsClient from 'react-stomp';


export default class Login extends Component{


    login(event) {
        event.preventDefault();
        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;

        axios.post('http://localhost:8080/user/login', {username:username,password:password}).then((response) => {

            Cookie.set("user",JSON.stringify(response.data))
            Cookie.set("userId",response.data.id)
            Cookie.set("role",response.data.role.toString())
            let autentificat
            if(response.data!=null){
                autentificat=true;

            }
            else{
                autentificat=false;
            }

            console.log(Cookie.get("userId").toString())
            console.log(Cookie.get("role").toString())
            console.log(JSON.parse(Cookie.get("user")))
            this.props.changeAutentificat(autentificat)
            window.location.href="http://localhost:3000/"
        });


    }

    constructor(props) {
        super(props);
        this.state={
            "username":String,
            "password":String
        }

        this.login = this.login.bind(this);
    }

    // sendMessage = (msg) => {
    //     this.clientRef.sendMessage('/topics/all', msg);
    // }

    render(){
        return (<div>
            <form onSubmit={this.login}>
                <label>Name:</label>
                <input
                    id="username"
                    name="username"
                    type="text"
                    placeholder="Enter your username"

                />
                <br/>
                <label>Password:</label>
                <input
                    id="password"
                    name="password"
                    type="password"
                    placeholder="Enter your password"
                />
                <br/>
                <button >
                    Login
                </button>
            </form>

                {/*<SockJsClient*/}
                {/*              url='http://localhost:8080/ws' topics={['/topic']}*/}
                {/*              onMessage={(msg) => {*/}
                {/*                  console.log(msg)*/}
                {/*                  alert(msg.activityName); }}*/}
                {/*              ref={ (client) => { this.clientRef = client }} />*/}
        </div>


        );


    }
}