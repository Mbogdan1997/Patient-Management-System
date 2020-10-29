import {Button, Table} from "reactstrap";
import React from "react";
import axios from "axios";
import Cookie from "js-cookie";

export  default class IntervalIntakeView extends React.Component {

    state = {
        intervalsIntake: []
    }

    makeTable() {
        console.log(Cookie.get("medicationPlanId"))

        var id = parseInt(Cookie.get("medicationPlanId"))
        axios.get('http://localhost:8080/intakeInterval/' + id,
            {
                headers: {
                    'Accept': "application/json",
                    'Content-Type': "application/json"
                }
            })
            .then((response) => {
                    console.log(response.data)
                    this.setState({intervalsIntake: response.data})
                    console.log(response.data)
                    console.log("asdklsdkasdkjdsaklsadkjaskjsdkjdsakjasdk")
                }
            );
    }

    componentDidMount() {
        this.makeTable();
    }

    render() {
        if (Cookie.get("role") === "CAREGIVER" || Cookie.get("role") === "PATIENT") {
            let drugsInMedicalPlan = this.state.intervalsIntake.map((drug) => {
                return (
                    <tr>
                        <td>{drug.drugDTO.name}</td>
                        <td>{drug.drugDTO.dossage}</td>
                        <td>{drug.drugDTO.sideEffects}</td>
                        <td>{drug.medicationPlanDTO.doctorDTO.name}</td>
                        <td>{drug.startHour}</td>
                        <td>{drug.finishHour}</td>
                    </tr>
                )
            });

            return (<div className="App container"><Table>
                    <thead>
                    <tr>
                        <th>Drug Name</th>
                        <th>Dossage</th>
                        <th>Side Effects</th>
                        <th>Doctor Name</th>
                        <th>Start Hour</th>
                        <th>Finish Hour</th>
                    </tr>
                    </thead>

                    <tbody>
                    {drugsInMedicalPlan}
                    </tbody>
                </Table>
                </div>
            );
        } else {
            return (
                <div>
                    <h1> Nu aveti acces la aceasta pagina</h1>
                </div>
            );
        }
    }

 }