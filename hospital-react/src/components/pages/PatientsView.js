import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import MyButton from "./MyButton";
import Cookie from "js-cookie";

class PatientsView extends Component {



    state = {
        patients: [],
        newPatientData: {
            name: '',
            birthDate: '',
            gender: '',
            adress: '',
            username: '',
            password: '',
            role: 'PATIENT'
        },
        editPatientData: {
            id: '',
            name: '',
            birthDate: '',
            gender: '',
            adress: '',
            username: '',
            password: '',
            role: 'PATIENT'
        },
        medicationPlanData:{
            patientDTO:{
                id: '',
                name: '',
                birthDate: '',
                gender: '',
                adress: '',
                username: '',
                password: '',
                role: 'PATIENT'
            },
            doctorDTO:{
                id: '',
                name: '',
                birthDate: '',
                gender: '',
                adress: '',
                username: '',
                password: '',
                role: 'DOCTOR'
            },
            startDate:'',
            finishDate:''
        },
        newPatientModal: false,
        editPatientModal: false,
        medicationPlanModal:false
    }
    componentWillMount() {
        this._refreshPatients();
    }

    toggleMedicationPlanModal() {
        this.setState({
            medicationPlanModal: ! this.state.medicationPlanModal
        });
    }
    toggleNewPatientModal() {
        this.setState({
            newPatientModal: ! this.state.newPatientModal
        });
    }
    toggleEditPatientModal() {
        this.setState({
            editPatientModal: ! this.state.editPatientModal
        });
    }
    addPatient() {
        axios.post('http://localhost:8080/patient/', this.state.newPatientData).then((response) => {
            let { patients } = this.state;
            console.log(response.data)

            patients.push(response.data);

            this.setState({ patients, newPatientModal: false, newPatientData: {
                    name: '',
                    birthDate: '',
                    gender: '',
                    adress: '',
                    username: '',
                    password: '',
                    role:'PATIENT'
                }});
        });
    }
    updatePatient() {
        let { id,name,birthDate,gender,adress,username,password,role} = this.state.editPatientData;
        console.log( { id,name,birthDate,gender,adress,username,password,role})
        axios.put('http://localhost:8080/patient/', {
            id,name,birthDate,gender,adress,username,password,role
        }).then((response) => {
            console.log(response.data)

            this._refreshPatients();

            this.setState({
                editPatientModal: false, editPatientData: { id: '',
                    name: '',
                    birthDate: '',
                    gender: '',
                    adress: '',
                    username: '',
                    password: '',
                    role:'PATIENT'
                }
            })
        });
    }
    editPatient(id,name,birthDate,gender,adress,username,password,role) {
        this.setState({
            editPatientData: { id,name,birthDate,gender,adress,username,password,role }, editPatientModal: ! this.state.editPatientModal
        });
    }

    makeMedicationPlan(patient) {
        let { medicationPlanData } = this.state;

        medicationPlanData.patientDTO = patient;
        medicationPlanData.doctorDTO=JSON.parse(Cookie.get("user"))

        this.setState({  medicationPlanData:medicationPlanData,medicationPlanModal: !this.state.medicationPlanModal })
    }

    deletePatient(id,role) {
        console.log(id)
        axios.delete('http://localhost:8080/patient/' + id,{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            }}).then((response) => {
            this._refreshPatients();
        });
    }
    _refreshPatients() {
        axios.get('http://localhost:8080/patient').then((response) => {
            this.setState({
                patients: response.data
            })
        });
    }

    routeChange() {
        console.log(this.state.medicationPlanData)
        axios.post('http://localhost:8080/medicationPlan', this.state.medicationPlanData).then((response) => {
            console.log(response.data.id)
            localStorage.setItem("medicationPlan", JSON.stringify(response.data))
            window.location.href = "http://localhost:3000/patient/medicationplans"

            this.setState({  medicationPlanModal: false, medicationPlanData:{
                    patient:{
                        id: '',
                        name: '',
                        birthDate: '',
                        gender: '',
                        adress: '',
                        username: '',
                        password: '',
                        role:'PATIENT'
                    },
                    doctor:{
                        id: '',
                        name: '',
                        birthDate: '',
                        gender: '',
                        adress: '',
                        username: '',
                        password: '',
                        role:'DOCTOR'
                    },
                    startDate:'',
                    finishDate:''
                }});
        });
    }

    render() {
        if(Cookie.get("role")==="DOCTOR") {
            let patients = this.state.patients.map((patient) => {
                return (
                    <tr key={patient.id}>
                        <td>{patient.id}</td>
                        <td>{patient.name}</td>
                        <td>{patient.birthDate}</td>
                        <td>{patient.gender}</td>
                        <td>{patient.adress}</td>
                        <td>{patient.username}</td>
                        <td>{patient.password}</td>
                        <td>{patient.role}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.editPatient.bind(this, patient.id, patient.name, patient.birthDate, patient.gender, patient.adress, patient.username, patient.password, patient.role)}>Edit</Button>
                            <Button color="danger" size="sm"
                                    onClick={this.deletePatient.bind(this, patient.id, patient.role)}>Delete</Button>
                            <Button className="my-3" color="primary"
                                    onClick={this.makeMedicationPlan.bind(this, patient)}>Make Medication Plan</Button>
                        </td>
                    </tr>
                )
            });
            return (
                <div className="App container">

                    <h1>Patients</h1>

                    <Button className="my-3" color="primary" onClick={this.toggleNewPatientModal.bind(this)}>Add
                        Patient</Button>

                    <Modal isOpen={this.state.newPatientModal} toggle={this.toggleNewPatientModal.bind(this)}>
                        <ModalHeader toggle={this.toggleNewPatientModal.bind(this)}>Add a new patient</ModalHeader>
                        <ModalBody>
                            {/*<FormGroup>*/}
                            {/*    <Label for="id">Id</Label>*/}
                            {/*    <Input id="id" value={this.state.newPatientData.id} onChange={(e) => {*/}
                            {/*        let { newPatientData } = this.state;*/}

                            {/*        newPatientData.id = e.target.value;*/}

                            {/*        this.setState({ newPatientData });*/}
                            {/*    }} />*/}
                            {/*</FormGroup>*/}
                            <FormGroup>
                                <Label for="name">Name</Label>
                                <Input id="name" value={this.state.newPatientData.name} onChange={(e) => {
                                    let {newPatientData} = this.state;

                                    newPatientData.name = e.target.value;

                                    this.setState({newPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="birthDate">Birth Date</Label>
                                <Input id="birthDate" value={this.state.newPatientData.birthDate} onChange={(e) => {
                                    let {newPatientData} = this.state;

                                    newPatientData.birthDate = e.target.value;

                                    this.setState({newPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="gender">Gender</Label>
                                <Input id="gender" value={this.state.newPatientData.gender} onChange={(e) => {
                                    let {newPatientData} = this.state;

                                    newPatientData.gender = e.target.value;

                                    this.setState({newPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="adress">Adress</Label>
                                <Input id="adress" value={this.state.newPatientData.adress} onChange={(e) => {
                                    let {newPatientData} = this.state;

                                    newPatientData.adress = e.target.value;

                                    this.setState({newPatientData});
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="username">Username</Label>
                                <Input id="username" value={this.state.newPatientData.username} onChange={(e) => {
                                    let {newPatientData} = this.state;

                                    newPatientData.username = e.target.value;

                                    this.setState({newPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="password">Password</Label>
                                <Input id="password" value={this.state.newPatientData.password} onChange={(e) => {
                                    let {newPatientData} = this.state;

                                    newPatientData.password = e.target.value;

                                    this.setState({newPatientData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.addPatient.bind(this)}>Add Patient</Button>{' '}
                            <Button color="secondary" onClick={this.toggleNewPatientModal.bind(this)}>Cancel</Button>

                        </ModalFooter>
                    </Modal>

                    <Modal isOpen={this.state.editPatientModal} toggle={this.toggleEditPatientModal.bind(this)}>
                        <ModalHeader toggle={this.toggleEditPatientModal.bind(this)}>Edit a new patient</ModalHeader>
                        <ModalBody>
                            <FormGroup>
                                <Label for="id">Id</Label>
                                <Input id="id" value={this.state.editPatientData.id} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.id = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="name">Name</Label>
                                <Input id="name" value={this.state.editPatientData.name} onChange={(e) => {
                                    let {editPatientData} = this.state;

                                    editPatientData.name = e.target.value;

                                    this.setState({editPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="birthDate">Birth Date</Label>
                                <Input id="birthDate" value={this.state.editPatientData.birthDate} onChange={(e) => {
                                    let {editPatientData} = this.state;

                                    editPatientData.birthDate = e.target.value;

                                    this.setState({editPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="gender">Gender</Label>
                                <Input id="gender" value={this.state.editPatientData.gender} onChange={(e) => {
                                    let {editPatientData} = this.state;

                                    editPatientData.gender = e.target.value;

                                    this.setState({editPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="adress">Adress</Label>
                                <Input id="adress" value={this.state.editPatientData.adress} onChange={(e) => {
                                    let {editPatientData} = this.state;

                                    editPatientData.adress = e.target.value;

                                    this.setState({editPatientData});
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="username">Username</Label>
                                <Input id="username" value={this.state.editPatientData.username} onChange={(e) => {
                                    let {editPatientData} = this.state;

                                    editPatientData.username = e.target.value;

                                    this.setState({editPatientData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="password">Password</Label>
                                <Input id="password" value={this.state.editPatientData.password} onChange={(e) => {
                                    let {editPatientData} = this.state;

                                    editPatientData.password = e.target.value;

                                    this.setState({editPatientData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.updatePatient.bind(this)}>Update Patient</Button>{' '}
                            <Button color="secondary" onClick={this.toggleEditPatientModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>

                    <Modal isOpen={this.state.medicationPlanModal} toggle={this.toggleMedicationPlanModal.bind(this)}>
                        <ModalHeader toggle={this.toggleMedicationPlanModal.bind(this)}>Make Medication
                            Plan</ModalHeader>
                        <ModalBody>
                            <FormGroup>
                                <Label for="startDate">Start Date</Label>
                                <Input id="startDate" value={this.state.medicationPlanData.startDate} onChange={(e) => {
                                    let {medicationPlanData} = this.state;

                                    medicationPlanData.startDate = e.target.value;

                                    this.setState({medicationPlanData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="finishDate">Finish Date</Label>
                                <Input id="finishDate" value={this.state.medicationPlanData.finishDate}
                                       onChange={(e) => {
                                           let {medicationPlanData} = this.state;

                                           medicationPlanData.finishDate = e.target.value;

                                           this.setState({medicationPlanData});
                                       }}/>
                            </FormGroup>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.routeChange.bind(this)}>Make Medication
                                Plan</Button>{' '}
                            <Button color="secondary" onClick={this.toggleEditPatientModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>


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
                </div>
            );
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

export default PatientsView;