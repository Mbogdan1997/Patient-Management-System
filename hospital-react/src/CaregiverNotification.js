import * as React from "react";
import Cookie from "js-cookie";
import SockJsClient from 'react-stomp';
import axios from "axios";


export default class CaregiverNotification extends React.Component{

    sendMessage = (msg) => {
        this.clientRef.sendMessage('/topics/all', msg);
    }

    render() {
        if(Cookie.get("role")==="CAREGIVER"){

            return (
              <div>
                  <SockJsClient
                      url='http://localhost:8080/ws' topics={['/topic']}
                      onMessage={(msg) => {
                          axios.get('http://localhost:8080/caregiverHasPatient/'+parseInt(Cookie.get("userId"))+'/'+
                              msg.patientId
                          ).then((response) => {
                              if (response.data===true){
                                  console.log(msg)
                                  alert("The patient " + msg.patientName + ' was '+ msg.activityName + ' for ' + msg.period);

                              }

                          });
                          }}
                      ref={ (client) => { this.clientRef = client }} />
              </div>
            );

        }
            return (
                <div>

                </div>
            );
    }

}