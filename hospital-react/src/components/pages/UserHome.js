import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import Cookie from "js-cookie";

export default class UserHome extends Component {

    state = {
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
        medicationPlanList: [],
        editPatientModal: false,
    }

    viewMedicationPlan(){
        axios.get('http://localhost:8080/medicationPlan/'+Cookie.get("userId")

        ).then((response) => {
            this.setState({medicationPlanList: response.data})
            console.log(response.data)
            console.log(this.state.medicationPlanList)
        });

    }

   loadUser(){
        axios.get('http://localhost:8080/patient/'+Cookie.get("userId")

        ).then((response) => {
            this.setState({editPatientData: response.data})
            console.log(response.data)
            console.log(this.state.editPatientData)
        });

    }

    componentDidMount() {
        this.loadUser()
        this.viewMedicationPlan()

    }

    toggleEditPatientModal() {
        this.setState({
            editPatientModal: !this.state.editPatientModal
        });
    }
    editPatient2() {
        this.setState({
            editPatientModal: !this.state.editPatientModal
        });
    }

    viewMedicationPlan1(medicationPlanId){
         Cookie.set("medicationPlanId",medicationPlanId)
         window.location.href="http://localhost:3000/medicationplans/intervalIntake"
        console.log("Nu face nimic"+medicationPlanId)
    }

    render() {

        if(Cookie.get("role")==="PATIENT") {
            let medicationPlans = this.state.medicationPlanList.map((medicationPlan) => {
                return (


                    <tr key={medicationPlan.id}>
                        <td>{medicationPlan.id}</td>
                        <td>{medicationPlan.doctorDTO.id}</td>
                        <td>{medicationPlan.doctorDTO.name}</td>
                        <td>{medicationPlan.patientDTO.id}</td>
                        <td>{medicationPlan.patientDTO.name}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.viewMedicationPlan1.bind(this, medicationPlan.id)}>View Medication
                                Plan</Button>
                        </td>
                        {/*    <td>*/}
                        {/*    /!*<Button color="success" size="sm" className="mr-2"*!/*/}
                        {/*    /!*        onClick={this.viewIntervalIntakeForMedicationPlan.bind(this, medicationPlan.id)}>View*!/*/}
                        {/*    /!*    Drugs Interval</Button>*!/*/}
                        {/*</td>*/}
                    </tr>

                )
            });

            // editPatient2(){
            //     this.setState({
            //         editPatientModal: !this.state.editPatientModal
            //     });
            // }


            return (

                <div className="App container">
                    <h1>Medication Plans</h1>
                    <Button color="success" size="sm" className="mr-2" onClick={this.editPatient2.bind(this)}>View
                        Profile</Button>
                    <Table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Id Doctor</th>
                            <th>Nume Doctor</th>
                            <th>Id Pacient</th>
                            <th>Nume Pacient</th>
                        </tr>
                        </thead>

                        <tbody>
                        {medicationPlans}
                        </tbody>
                    </Table>
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
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.name = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="birthDate">Birth Date</Label>
                                <Input id="birthDate" value={this.state.editPatientData.birthDate} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.birthDate = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="gender">Gender</Label>
                                <Input id="gender" value={this.state.editPatientData.gender} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.gender = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="adress">Adress</Label>
                                <Input id="adress" value={this.state.editPatientData.adress} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.adress = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="username">Username</Label>
                                <Input id="username" value={this.state.editPatientData.username} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.username = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="password">Password</Label>
                                <Input id="password" value={this.state.editPatientData.password} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.password = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="secondary" onClick={this.toggleEditPatientModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>

                </div>

            )
                ;
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