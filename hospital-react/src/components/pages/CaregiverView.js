import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import MyButton from "./MyButton";
import Cookie from "js-cookie";

class CaregiversView extends Component {



    state = {
        caregivers: [],
        newCaregiverData: {
            name: '',
            birthDate: '',
            gender: '',
            adress: '',
            username: '',
            password: '',
            role: 'CAREGIVER'
        },
        editCaregiverData: {
            id: '',
            name: '',
            birthDate: '',
            gender: '',
            adress: '',
            username: '',
            password: '',
            role: 'CAREGIVER'
        },

        newCaregiverModal: false,
        editCaregiverModal: false,
        medicationPlanModal:false
    }
    componentWillMount() {
        this._refreshCaregivers();
    }

    toggleMedicationPlanModal() {
        this.setState({
            medicationPlanModal: ! this.state.medicationPlanModal
        });
    }
    toggleNewCaregiverModal() {
        this.setState({
            newCaregiverModal: ! this.state.newCaregiverModal
        });
    }
    toggleEditCaregiverModal() {
        this.setState({
            editCaregiverModal: ! this.state.editCaregiverModal
        });
    }
    addCaregiver() {
        axios.post('http://localhost:8080/caregiver/', this.state.newCaregiverData).then((response) => {
            let { caregivers } = this.state;
            console.log(response.data)

            caregivers.push(response.data);

            this.setState({ caregivers, newCaregiverModal: false, newCaregiverData: {
                    name: '',
                    birthDate: '',
                    gender: '',
                    adress: '',
                    username: '',
                    password: '',
                    role: 'CAREGIVER'
                }});
        });
    }
    updateCaregiver() {
        let { id,name,birthDate,gender,adress,username,password,role} = this.state.editCaregiverData;
        console.log( { id,name,birthDate,gender,adress,username,password,role})
        axios.put('http://localhost:8080/caregiver/', {
            id,name,birthDate,gender,adress,username,password,role
        }).then((response) => {
            console.log(response.data)

            this._refreshCaregivers();

            this.setState({
                editCaregiverModal: false, editCaregiverData: { id: '',
                    name: '',
                    birthDate: '',
                    gender: '',
                    adress: '',
                    username: '',
                    password: '',
                    role: 'CAREGIVER' }
            })
        });
    }
    editCaregiver(id,name,birthDate,gender,adress,username,password,role) {
        this.setState({
            editCaregiverData: { id,name,birthDate,gender,adress,username,password,role }, editCaregiverModal: ! this.state.editCaregiverModal
        });
    }

    // makeMedicationPlan(caregiver) {
    //     let { medicationPlanData } = this.state;
    //
    //     medicationPlanData.caregiverDTO = caregiver;
    //     medicationPlanData.doctorDTO=JSON.parse(Cookie.get("user"))
    //
    //     this.setState({  medicationPlanData:medicationPlanData,medicationPlanModal: !this.state.medicationPlanModal })
    // }

    deleteCaregiver(id,role) {
        console.log(id)
        axios.delete('http://localhost:8080/caregiver/' + id,{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            }}).then((response) => {
            this._refreshCaregivers();
        });
    }
    _refreshCaregivers() {
        axios.get('http://localhost:8080/caregiver').then((response) => {
            this.setState({
                caregivers: response.data
            })
        });
    }



    routeChange(id){
        Cookie.set("caregiverId",JSON.stringify(id))
        console.log(id)
        console.log(JSON.parse(Cookie.get("caregiverId")))
        window.location.href = "http://localhost:3000/caregiver/viewPatients"


    }

    render() {
        if(Cookie.get("role")==="DOCTOR") {
            let caregivers = this.state.caregivers.map((caregiver) => {
                return (
                    <tr key={caregiver.id}>
                        <td>{caregiver.id}</td>
                        <td>{caregiver.name}</td>
                        <td>{caregiver.birthDate}</td>
                        <td>{caregiver.gender}</td>
                        <td>{caregiver.adress}</td>
                        <td>{caregiver.username}</td>
                        <td>{caregiver.password}</td>
                        <td>{caregiver.role}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.editCaregiver.bind(this, caregiver.id, caregiver.name, caregiver.birthDate, caregiver.gender, caregiver.adress, caregiver.username, caregiver.password, caregiver.role)}>Edit</Button>
                            <Button color="danger" size="sm"
                                    onClick={this.deleteCaregiver.bind(this, caregiver.id, caregiver.role)}>Delete</Button>
                            <Button className="my-3" color="primary"
                                    onClick={this.routeChange.bind(this, caregiver.id)}>Update Patient List</Button>
                        </td>
                    </tr>
                )
            });
            return (
                <div className="App container">

                    <h1>Caregivers</h1>

                    <Button className="my-3" color="primary" onClick={this.toggleNewCaregiverModal.bind(this)}>Add
                        Caregiver</Button>

                    <Modal isOpen={this.state.newCaregiverModal} toggle={this.toggleNewCaregiverModal.bind(this)}>
                        <ModalHeader toggle={this.toggleNewCaregiverModal.bind(this)}>Add a new caregiver</ModalHeader>
                        <ModalBody>
                            {/*<FormGroup>*/}
                            {/*    <Label for="id">Id</Label>*/}
                            {/*    <Input id="id" value={this.state.newCaregiverData.id} onChange={(e) => {*/}
                            {/*        let { newCaregiverData } = this.state;*/}

                            {/*        newCaregiverData.id = e.target.value;*/}

                            {/*        this.setState({ newCaregiverData });*/}
                            {/*    }} />*/}
                            {/*</FormGroup>*/}
                            <FormGroup>
                                <Label for="name">Name</Label>
                                <Input id="name" value={this.state.newCaregiverData.name} onChange={(e) => {
                                    let {newCaregiverData} = this.state;

                                    newCaregiverData.name = e.target.value;

                                    this.setState({newCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="birthDate">Birth Date</Label>
                                <Input id="birthDate" value={this.state.newCaregiverData.birthDate} onChange={(e) => {
                                    let {newCaregiverData} = this.state;

                                    newCaregiverData.birthDate = e.target.value;

                                    this.setState({newCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="gender">Gender</Label>
                                <Input id="gender" value={this.state.newCaregiverData.gender} onChange={(e) => {
                                    let {newCaregiverData} = this.state;

                                    newCaregiverData.gender = e.target.value;

                                    this.setState({newCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="adress">Adress</Label>
                                <Input id="adress" value={this.state.newCaregiverData.adress} onChange={(e) => {
                                    let {newCaregiverData} = this.state;

                                    newCaregiverData.adress = e.target.value;

                                    this.setState({newCaregiverData});
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="username">Username</Label>
                                <Input id="username" value={this.state.newCaregiverData.username} onChange={(e) => {
                                    let {newCaregiverData} = this.state;

                                    newCaregiverData.username = e.target.value;

                                    this.setState({newCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="password">Password</Label>
                                <Input id="password" value={this.state.newCaregiverData.password} onChange={(e) => {
                                    let {newCaregiverData} = this.state;

                                    newCaregiverData.password = e.target.value;

                                    this.setState({newCaregiverData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.addCaregiver.bind(this)}>Add Caregiver</Button>{' '}
                            <Button color="secondary" onClick={this.toggleNewCaregiverModal.bind(this)}>Cancel</Button>

                        </ModalFooter>
                    </Modal>

                    <Modal isOpen={this.state.editCaregiverModal} toggle={this.toggleEditCaregiverModal.bind(this)}>
                        <ModalHeader toggle={this.toggleEditCaregiverModal.bind(this)}>Edit a new
                            caregiver</ModalHeader>
                        <ModalBody>
                            <FormGroup>
                                <Label for="id">Id</Label>
                                <Input id="id" value={this.state.editCaregiverData.id} onChange={(e) => {
                                    // let { editCaregiverData } = this.state;
                                    //
                                    // editCaregiverData.id = e.target.value;
                                    //
                                    // this.setState({ editCaregiverData });
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="name">Name</Label>
                                <Input id="name" value={this.state.editCaregiverData.name} onChange={(e) => {
                                    let {editCaregiverData} = this.state;

                                    editCaregiverData.name = e.target.value;

                                    this.setState({editCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="birthDate">Birth Date</Label>
                                <Input id="birthDate" value={this.state.editCaregiverData.birthDate} onChange={(e) => {
                                    let {editCaregiverData} = this.state;

                                    editCaregiverData.birthDate = e.target.value;

                                    this.setState({editCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="gender">Gender</Label>
                                <Input id="gender" value={this.state.editCaregiverData.gender} onChange={(e) => {
                                    let {editCaregiverData} = this.state;

                                    editCaregiverData.gender = e.target.value;

                                    this.setState({editCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="adress">Adress</Label>
                                <Input id="adress" value={this.state.editCaregiverData.adress} onChange={(e) => {
                                    let {editCaregiverData} = this.state;

                                    editCaregiverData.adress = e.target.value;

                                    this.setState({editCaregiverData});
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="username">Username</Label>
                                <Input id="username" value={this.state.editCaregiverData.username} onChange={(e) => {
                                    let {editCaregiverData} = this.state;

                                    editCaregiverData.username = e.target.value;

                                    this.setState({editCaregiverData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="password">Password</Label>
                                <Input id="password" value={this.state.editCaregiverData.password} onChange={(e) => {
                                    let {editCaregiverData} = this.state;

                                    editCaregiverData.password = e.target.value;

                                    this.setState({editCaregiverData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.updateCaregiver.bind(this)}>Update
                                Caregiver</Button>{' '}
                            <Button color="secondary" onClick={this.toggleEditCaregiverModal.bind(this)}>Cancel</Button>
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
                        {caregivers}
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

export default CaregiversView;