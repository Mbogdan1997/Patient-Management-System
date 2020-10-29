import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import Cookie from "js-cookie";

class DrugsView extends Component {
    state = {
        drugs: [],
        newDrugData: {
           name: '',
            dossage:'',
            sideEffects: '',
        },
        editDrugData: {
            id:'',
            name: '',
            dossage:'',
            sideEffects: '',
        },
        newDrugModal: false,
        editDrugModal: false
    }
    componentWillMount() {
        this._refreshDrugs();
    }
    toggleNewDrugModal() {
        this.setState({
            newDrugModal: ! this.state.newDrugModal
        });
    }
    toggleEditDrugModal() {
        this.setState({
            editDrugModal: ! this.state.editDrugModal
        });
    }
    addDrug() {
        axios.post('http://localhost:8080/drug', this.state.newDrugData,{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            }}).then((response) => {
            let { drugs } = this.state;

            drugs.push(response.data);

            this.setState({ drugs, newDrugModal: false, newDrugData: {
                    name: '',
                    dossage: '',
                    sideEffects: ''
                }});
        });
    }
    updateDrug() {
        let {id, name, dossage,sideEffects } = this.state.editDrugData;

        axios.put('http://localhost:8080/drug', {
            id,name, dossage,sideEffects
        },{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json",
                'Access-Control-Allow-Credentials':"*"
            }}).then((response) => {
            this._refreshDrugs();

            this.setState({
                editDrugModal: false, editDrugData: { id: '', name: '', dossage: '' ,sideEffects: ''}
            })
        });
    }
    editDrug(id, name, dossage,sideEffects) {
        this.setState({
            editDrugData: { id, name, dossage,sideEffects }, editDrugModal: ! this.state.editDrugModal
        });
    }
    deleteDrug(id) {
        axios.delete('http://localhost:8080/drug/' + id,{headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            }}).then((response) => {
            this._refreshDrugs();
        });
    }
    _refreshDrugs() {
        axios.get('http://localhost:8080/drug').then((response) => {
            this.setState({
                drugs: response.data
            })
        });
    }
    render() {
        if(Cookie.get("role")==="DOCTOR") {
            let drugs = this.state.drugs.map((drug) => {
                return (
                    <tr key={drug.id}>
                        <td>{drug.id}</td>
                        <td>{drug.name}</td>
                        <td>{drug.dossage}</td>
                        <td>{drug.sideEffects}</td>
                        <td>
                            <Button color="success" size="sm" className="mr-2"
                                    onClick={this.editDrug.bind(this, drug.id, drug.name, drug.dossage, drug.sideEffects)}>Edit</Button>
                            <Button color="danger" size="sm"
                                    onClick={this.deleteDrug.bind(this, drug.id)}>Delete</Button>
                        </td>
                    </tr>
                )
            });
            return (
                <div className="App container">

                    <h1>Drugs</h1>

                    <Button className="my-3" color="primary" onClick={this.toggleNewDrugModal.bind(this)}>Add
                        Drug</Button>

                    <Modal isOpen={this.state.newDrugModal} toggle={this.toggleNewDrugModal.bind(this)}>
                        <ModalHeader toggle={this.toggleNewDrugModal.bind(this)}>Add a new drug</ModalHeader>
                        <ModalBody>
                            <FormGroup>
                                <Label for="name">Name</Label>
                                <Input id="name" value={this.state.newDrugData.name} onChange={(e) => {
                                    let {newDrugData} = this.state;

                                    newDrugData.name = e.target.value;

                                    this.setState({newDrugData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="dossage">Dossage</Label>
                                <Input id="dossage" value={this.state.newDrugData.dossage} onChange={(e) => {
                                    let {newDrugData} = this.state;

                                    newDrugData.dossage = e.target.value;

                                    this.setState({newDrugData});
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="sideEffects">Side Effects</Label>
                                <Input id="sideEffects" value={this.state.newDrugData.sideEffects} onChange={(e) => {
                                    let {newDrugData} = this.state;

                                    newDrugData.sideEffects = e.target.value;

                                    this.setState({newDrugData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.addDrug.bind(this)}>Add Drug</Button>{' '}
                            <Button color="secondary" onClick={this.toggleNewDrugModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>

                    <Modal isOpen={this.state.editDrugModal} toggle={this.toggleEditDrugModal.bind(this)}>
                        <ModalHeader toggle={this.toggleEditDrugModal.bind(this)}>Edit a new drug</ModalHeader>
                        <ModalBody>

                            <FormGroup>
                                <Label for="id">Id</Label>
                                <Input id="id" value={this.state.editDrugData.id} onChange={(e) => {
                                    // let { editPatientData } = this.state;
                                    //
                                    // editPatientData.id = e.target.value;
                                    //
                                    // this.setState({ editPatientData });
                                }}/>
                            </FormGroup>


                            <FormGroup>
                                <Label for="name">Name</Label>
                                <Input id="name" value={this.state.editDrugData.name} onChange={(e) => {
                                    let {editDrugData} = this.state;

                                    editDrugData.name = e.target.value;

                                    this.setState({editDrugData});
                                }}/>
                            </FormGroup>
                            <FormGroup>
                                <Label for="dossage">Dossage</Label>
                                <Input id="dossage" value={this.state.editDrugData.dossage} onChange={(e) => {
                                    let {editDrugData} = this.state;

                                    editDrugData.dossage = e.target.value;

                                    this.setState({editDrugData});
                                }}/>
                            </FormGroup>

                            <FormGroup>
                                <Label for="sideEffects">Side Effects</Label>
                                <Input id="sideEffects" value={this.state.editDrugData.sideEffects} onChange={(e) => {
                                    let {editDrugData} = this.state;

                                    editDrugData.sideEffects = e.target.value;

                                    this.setState({editDrugData});
                                }}/>
                            </FormGroup>

                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onClick={this.updateDrug.bind(this)}>Update Drug</Button>{' '}
                            <Button color="secondary" onClick={this.toggleEditDrugModal.bind(this)}>Cancel</Button>
                        </ModalFooter>
                    </Modal>


                    <Table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Dossage</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        {drugs}
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

export default DrugsView;
