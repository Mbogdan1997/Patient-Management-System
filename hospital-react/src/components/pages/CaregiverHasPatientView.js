import * as React from "react";
import axios from "axios";
import {Button, Table} from "reactstrap";
import Cookie from "js-cookie";


export default class CaregiverHasPatientView extends React.Component{

    state = {
        patientsInList:[],
        allPatients:[],

    }

    componentDidMount() {

        this._refreshPatients()
        this._refreshPatientsFromList()

    }

    _refreshPatients() {
        axios.get('http://localhost:8080/patient/caregiver/not/'+parseInt(Cookie.get("caregiverId"))).then((response) => {
            this.setState({

                allPatients: response.data
            })
            console.log(this.state.allPatients)
        });
    }
    _refreshPatientsFromList(){
        console.log(Cookie.get("caregiverId"))
        axios.get('http://localhost:8080/caregiverHasPatient/'+parseInt(Cookie.get("caregiverId"))).then((response) => {


            this.setState({
                patientsInList: response.data
            })
        });

    }

    deletePatient(patientDTOID){
        axios.delete('http://localhost:8080/caregiverHasPatient/', {
            data: {
                patientDTO: {
                    id: patientDTOID
                },
                caregiverDTO: {
                    id: Cookie.get("caregiverId")
                }

            }
        }).then((response) => {
            this._refreshPatientsFromList()
            this._refreshPatients()
        });

    }

    addPatient(patientDTOID){
        console.log(Cookie.get("caregiverId"))
        axios.post('http://localhost:8080/caregiverHasPatient/',{
            patientDTO:{
                id:patientDTOID
            },
            caregiverDTO:{
                id:Cookie.get("caregiverId")
            }

        }).then((response) => {
            this._refreshPatientsFromList()
            this._refreshPatients()
        });

    }

    render() {
        if (Cookie.get("role") === "DOCTOR") {
            let patients = this.state.patientsInList.map((patientHasCaregiver) => {
                return (

                    <tr key={patientHasCaregiver.patientDTO.id}>
                        <td>{patientHasCaregiver.patientDTO.id}</td>
                        <td>{patientHasCaregiver.patientDTO.name}</td>
                        <td>{patientHasCaregiver.patientDTO.birthDate}</td>
                        <td>{patientHasCaregiver.patientDTO.gender}</td>
                        <td>{patientHasCaregiver.patientDTO.adress}</td>
                        <td>{patientHasCaregiver.patientDTO.username}</td>
                        <td>{patientHasCaregiver.patientDTO.password}</td>
                        <td>{patientHasCaregiver.patientDTO.role}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.deletePatient.bind(this, patientHasCaregiver.patientDTO.id)}>Delete</Button>
                        </td>
                    </tr>

                )
            });


            let patientsOutofList = this.state.allPatients.map((patientDTO) => {
                return (
                    <tr key={patientDTO.id}>
                        <td>{patientDTO.id}</td>
                        <td>{patientDTO.name}</td>
                        <td>{patientDTO.birthDate}</td>
                        <td>{patientDTO.gender}</td>
                        <td>{patientDTO.adress}</td>
                        <td>{patientDTO.username}</td>
                        <td>{patientDTO.password}</td>
                        <td>{patientDTO.role}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.addPatient.bind(this, patientDTO.id)}>Add</Button>
                        </td>
                    </tr>

                )
            });

            return (<div className="App container">
                <h1>Caregiver's Patients</h1>
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Birth Date</th>
                        <th>Gender</th>
                        <th>Adress</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Role</th>
                    </tr>
                    </thead>

                    <tbody>
                    {patients}
                    </tbody>
                </Table>
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Birth Date</th>
                        <th>Gender</th>
                        <th>Adress</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Role</th>
                    </tr>
                    </thead>

                    <tbody>
                    {patientsOutofList}
                    </tbody>
                </Table>
            </div>)
        }
        else{
            return (
                <div>
                    <h1> Nu aveti acces la aceasta pagina</h1>
                </div>
            );
        }
    }


}